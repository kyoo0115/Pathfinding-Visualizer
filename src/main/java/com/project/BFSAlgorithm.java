package com.project;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javax.swing.JPanel;

public class BFSAlgorithm implements PathfindingAlgorithm {

  @Override
  public void runAlgorithm(JPanel[][] grid, int startX, int startY, int endX, int endY,
      Map<Point, Point> checkpoints) {
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    Map<Point, Point> parentMap = new HashMap<>();
    performBFS(grid, startX, startY, endX, endY, visited, parentMap);
    markPath(grid, new Point(endX, endY), parentMap);
  }

  private void performBFS(JPanel[][] grid, int startX, int startY, int endX, int endY,
      boolean[][] visited, Map<Point, Point> parentMap) {
    Queue<Point> queue = new LinkedList<>();
    queue.add(new Point(startX, startY));
    visited[startX][startY] = true;

    while (!queue.isEmpty()) {
      Point current = queue.poll();
      int x = current.x, y = current.y;

      if (x == endX && y == endY) {
        grid[x][y].setBackground(Color.YELLOW);
        break;
      }

        if (isPathNode(grid, x, y)) {
            grid[x][y].setBackground(Color.BLUE);
        }

      for (Point dir : new Point[]{new Point(-1, 0), new Point(1, 0), new Point(0, -1),
          new Point(0, 1)}) {
        int newX = x + dir.x, newY = y + dir.y;
        if (isValidMove(newX, newY, visited, grid)) {
          queue.add(new Point(newX, newY));
          visited[newX][newY] = true;
          parentMap.put(new Point(newX, newY), current);

            if (isPathNode(grid, newX, newY)) {
                grid[newX][newY].setBackground(Color.GREEN);
            }
        }
      }
      pause();
    }
  }

  private void markPath(JPanel[][] grid, Point endPoint, Map<Point, Point> parentMap) {
    Point current = endPoint;
    while (current != null) {
      if (isPathNode(grid, current.x, current.y)) {
        grid[current.x][current.y].setBackground(Color.PINK);  // Mark final path
      }
      current = parentMap.get(current);
    }
  }

  private boolean isPathNode(JPanel[][] grid, int x, int y) {
    return !grid[x][y].getBackground().equals(Color.RED) && !grid[x][y].getBackground()
        .equals(Color.YELLOW);
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