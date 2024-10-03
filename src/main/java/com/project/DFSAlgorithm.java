package com.project;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.swing.JPanel;

public class DFSAlgorithm implements PathfindingAlgorithm {

  @Override
  public void runAlgorithm(JPanel[][] grid, int startX, int startY, int endX, int endY,
      Map<Point, Point> checkpoints) {
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    Map<Point, Point> parentMap = new HashMap<>();
    performDFS(grid, startX, startY, endX, endY, visited, parentMap);
    markFinalPath(grid, new Point(startX, startY), new Point(endX, endY), parentMap);
  }

  private void performDFS(JPanel[][] grid, int startX, int startY, int endX, int endY,
      boolean[][] visited, Map<Point, Point> parentMap) {
    Stack<Point> stack = new Stack<>();
    stack.push(new Point(startX, startY));
    parentMap.put(new Point(startX, startY), null);

    while (!stack.isEmpty()) {
      Point current = stack.pop();
      int x = current.x;
      int y = current.y;

        if (isOutOfBoundOrVisited(grid, x, y, visited)) {
            continue;
        }

      visited[x][y] = true;

      if (x == endX && y == endY) {
        grid[x][y].setBackground(Color.YELLOW);  // Mark end node
        break;
      }

      // Explore neighbors
      for (Point dir : new Point[]{new Point(1, 0), new Point(-1, 0), new Point(0, 1),
          new Point(0, -1)}) {
        int newX = x + dir.x, newY = y + dir.y;
        if (isValidMove(newX, newY, visited, grid)) {
          stack.push(new Point(newX, newY));
          parentMap.put(new Point(newX, newY), current);
          if (isPathNode(grid, newX, newY)) {
            grid[newX][newY].setBackground(Color.GREEN);  // Frontier nodes
          }
        }
      }

        if (isPathNode(grid, x, y)) {
            grid[x][y].setBackground(Color.BLUE);  // Fully explored nodes
        }
      pause();
    }
  }

  private void markFinalPath(JPanel[][] grid, Point start, Point end, Map<Point, Point> parentMap) {
    Point current = end;
    while (current != null && parentMap.containsKey(current)) {
      if (!current.equals(start) && !current.equals(end)) {  // Keep start and end as RED/YELLOW
        grid[current.x][current.y].setBackground(Color.PINK);  // Mark final path
      }
      current = parentMap.get(current);
    }

    grid[start.x][start.y].setBackground(Color.RED);
    grid[end.x][end.y].setBackground(Color.YELLOW);
  }

  private boolean isOutOfBoundOrVisited(JPanel[][] grid, int x, int y, boolean[][] visited) {
    return x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y];
  }

  private boolean isPathNode(JPanel[][] grid, int x, int y) {
    Color cellColor = grid[x][y].getBackground();
    return !cellColor.equals(Color.RED) && !cellColor.equals(Color.YELLOW);
  }

  private boolean isValidMove(int x, int y, boolean[][] visited, JPanel[][] grid) {
    return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && !visited[x][y]
        && !grid[x][y].getBackground().equals(Color.BLACK);
  }

  private void pause() {
    try {
      Thread.sleep(30);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}