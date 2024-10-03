package com.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class PathfindingVisualizer extends JFrame {

  private final int gridSize = 20;
  private final Grid grid;
  private final Map<Integer, Point> checkpoints = new TreeMap<>();
  private int currentCheckpoint = 1;
  private JTextArea scoreboard;

  public PathfindingVisualizer() {
    setTitle("Pathfinding Visualization - Kyoo Min");
    setSize(1000, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    grid = new Grid(gridSize);
    grid.setCheckpointHandler(this::placeCheckpoint);
    grid.setResetCheckpointsHandler(() -> {
      currentCheckpoint = 1;
      checkpoints.clear();
    });
    add(grid.getGridPanel(), BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(10, 1, 5, 5));  // Vertical layout for buttons

    addAlgorithmButtons(buttonPanel);
    addResetAndMazeButtons(buttonPanel);
    addScoreTextArea();

    add(buttonPanel, BorderLayout.EAST);
    setupKeyBindings();
    setVisible(true);
  }

  private void addScoreTextArea() {
    JPanel scorePanel = new JPanel();
    scorePanel.setLayout(new BorderLayout());
    scoreboard = new JTextArea();
    scoreboard.setEditable(false);
    scorePanel.add(new JLabel("Algorithm Execution Times:"), BorderLayout.NORTH);
    scorePanel.add(new JScrollPane(scoreboard), BorderLayout.CENTER);
    add(scorePanel, BorderLayout.SOUTH);  // Place the scoreboard at the bottom
  }

  private void addAlgorithmButtons(JPanel buttonPanel) {
    JButton startBFS = new JButton("Start BFS");
    startBFS.addActionListener(
        e -> new Thread(() -> runAndMeasureAlgorithm(new BFSAlgorithm(), "BFS")).start());
    buttonPanel.add(startBFS);

    JButton startDFS = new JButton("Start DFS");
    startDFS.addActionListener(
        e -> new Thread(() -> runAndMeasureAlgorithm(new DFSAlgorithm(), "DFS")).start());
    buttonPanel.add(startDFS);

    JButton startDijkstra = new JButton("Start Dijkstra");
    startDijkstra.addActionListener(
        e -> new Thread(() -> runAndMeasureAlgorithm(new DijkstraAlgorithm(), "Dijkstra")).start());
    buttonPanel.add(startDijkstra);

    JButton startAStar = new JButton("Start AStar");
    startAStar.addActionListener(
        e -> new Thread(() -> runAndMeasureAlgorithm(new AStarAlgorithm(), "A*")).start());
    buttonPanel.add(startAStar);
  }

  private void addResetAndMazeButtons(JPanel buttonPanel) {
    JButton generateMazeButton = new JButton("Generate Maze");
    generateMazeButton.addActionListener(
        e -> new Thread(() -> PathfindingUtils.generateMaze(grid, gridSize)).start());
    buttonPanel.add(generateMazeButton);

    JButton resetPathsButton = new JButton("Reset Paths");
    resetPathsButton.addActionListener(e -> new Thread(grid::resetPaths).start());
    buttonPanel.add(resetPathsButton);

    JButton resetButton = new JButton("Reset Board");
    resetButton.addActionListener(e -> {
      grid.resetGrid();
      resetCheckpoints();
    });
    buttonPanel.add(resetButton);
  }

  private void setupKeyBindings() {
    JRootPane rootPane = this.getRootPane();
    for (int i = 1; i <= 9; i++) {
      int checkpointNumber = i;
      rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
          .put(KeyStroke.getKeyStroke(String.valueOf(i)), "placeCheckpoint" + i);
      rootPane.getActionMap().put("placeCheckpoint" + i, new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
          currentCheckpoint = checkpointNumber;
        }
      });
    }
  }

  private void placeCheckpoint(int row, int col) {
    JPanel cell = grid.getCell(row, col);
    if (!cell.getBackground().equals(Color.BLACK)) {
      cell.setBackground(Color.RED);
      JLabel label = new JLabel(String.valueOf(currentCheckpoint));
      label.setForeground(Color.WHITE);
      cell.removeAll();
      cell.add(label);
      cell.revalidate();
      cell.repaint();
      checkpoints.put(currentCheckpoint, new Point(row, col));
      currentCheckpoint++;
    }
  }

  private void resetCheckpoints() {
    currentCheckpoint = 1;
    checkpoints.clear();

    for (int row = 0; row < gridSize; row++) {
      for (int col = 0; col < gridSize; col++) {
        JPanel cell = grid.getCell(row, col);
        cell.removeAll();  // Remove labels
        cell.revalidate();
        cell.repaint();
      }
    }
  }

  private void runAndMeasureAlgorithm(PathfindingAlgorithm algorithm, String algorithmName) {
    Map<Point, Point> pointCheckpoints = convertCheckpoints(checkpoints);
    long startTime = System.nanoTime();  // Start time tracking
    for (Map.Entry<Integer, Point> entry : checkpoints.entrySet()) {
      Point start = entry.getValue();
      if (entry.getKey() < checkpoints.size()) {
        Point end = checkpoints.get(entry.getKey() + 1);
        if (end != null) {
          algorithm.runAlgorithm(grid.getCells(), start.x, start.y, end.x, end.y, pointCheckpoints);
          repaint();
        }
      }
    }
    long endTime = System.nanoTime();
    double elapsedTimeInSeconds = (endTime - startTime) / 1e9;
    updateScoreboard(algorithmName, elapsedTimeInSeconds);
  }

  private Map<Point, Point> convertCheckpoints(Map<Integer, Point> checkpoints) {
    Map<Point, Point> pointMap = new HashMap<>();
    Point previous = null;
    for (int i = 1; i <= checkpoints.size(); i++) {
      Point current = checkpoints.get(i);
      if (previous != null) {
        pointMap.put(previous, current);
      }
      previous = current;
    }
    return pointMap;
  }

  private void updateScoreboard(String algorithmName, double timeTaken) {
    scoreboard.append(algorithmName + " Time: " + String.format("%.2f", timeTaken) + " seconds\n");
  }
}