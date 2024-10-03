package com.project;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.function.BiConsumer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Grid {

  private final int size;  // Grid size
  private final JPanel[][] cells;  // 2D Array of cells (JPanels)
  private final JPanel gridPanel;  // Parent panel to hold the grid
  private BiConsumer<Integer, Integer> checkpointHandler;  // To handle checkpoints placement
  private boolean drawing = false;  // Track drawing mode
  private boolean removing = false; // Track removal mode
  private Runnable resetCheckpointsCallback;

  public Grid(int size) {
    this.size = size;
    this.cells = new JPanel[size][size];
    this.gridPanel = new JPanel(new GridLayout(size, size));

    // Initialize each cell
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        cells[row][col] = createCell(row, col);
        gridPanel.add(cells[row][col]);
      }
    }

    addMouseListenersToGrid();
  }

  private JPanel createCell(int row, int col) {
    JPanel cell = new JPanel();
    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    cell.setBackground(Color.WHITE);
    return cell;
  }

  private void addMouseListenersToGrid() {
    gridPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        handleMousePress(e);
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        drawing = false;
        removing = false;
      }
    });

    gridPanel.addMouseMotionListener(new MouseMotionListener() {
      @Override
      public void mouseDragged(MouseEvent e) {
        handleMouseDrag(e);
      }

      @Override
      public void mouseMoved(MouseEvent e) {
        // Not used
      }
    });
  }

  private void handleMousePress(MouseEvent e) {
    int row = e.getY() / cells[0][0].getHeight();
    int col = e.getX() / cells[0][0].getWidth();

    if (SwingUtilities.isRightMouseButton(e)) {  // Right-click to add checkpoint
      if (checkpointHandler != null) {
        checkpointHandler.accept(row, col);
      }
    } else if (SwingUtilities.isLeftMouseButton(e)) {  // Left-click to start drawing walls
      JPanel cell = cells[row][col];
      if (!cell.getBackground().equals(Color.BLACK)) {
        cell.setBackground(Color.BLACK);
        drawing = true;
      } else {
        cell.setBackground(Color.WHITE);
        removing = true;
      }
    }
  }

  private void handleMouseDrag(MouseEvent e) {
    int row = e.getY() / cells[0][0].getHeight();
    int col = e.getX() / cells[0][0].getWidth();

    if (row >= 0 && row < size && col >= 0 && col < size) {
      JPanel cell = cells[row][col];
      if (drawing) {
        if (!cell.getBackground().equals(Color.BLACK)) {
          cell.setBackground(Color.BLACK);
        }
      } else if (removing) {
        if (cell.getBackground().equals(Color.BLACK)) {
          cell.setBackground(Color.WHITE);
        }
      }
    }
  }

  public JPanel getGridPanel() {
    return gridPanel;
  }

  public JPanel getCell(int row, int col) {
    return cells[row][col];
  }

  public void setCheckpointHandler(BiConsumer<Integer, Integer> handler) {
    this.checkpointHandler = handler;
  }

  public void resetGrid() {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        JPanel cell = cells[row][col];
        cell.setBackground(Color.WHITE);
        cell.removeAll();
        cell.revalidate();
        cell.repaint();
      }
    }

    if (resetCheckpointsCallback != null) {
      resetCheckpointsCallback.run();
    }
  }

  public void resetPaths() {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        JPanel cell = cells[row][col];
        Color cellColor = cell.getBackground();
        if (cellColor.equals(Color.BLUE) || cellColor.equals(Color.GREEN) ||
            cellColor.equals(Color.PINK) || cellColor.equals(Color.ORANGE)) {
          cell.setBackground(Color.WHITE);  // Reset path cells to White
        }
        cell.revalidate();
        cell.repaint();
      }
    }
  }

  public JPanel[][] getCells() {
    return cells;
  }

  public void setResetCheckpointsHandler(Runnable resetCheckpointsHandler) {
    this.resetCheckpointsCallback = resetCheckpointsHandler;
  }
}