package com.project;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class PathfindingUtils {

  public static void generateMaze(Grid grid, int gridSize) {
    for (int row = 0; row < gridSize; row++) {
      for (int col = 0; col < gridSize; col++) {
        grid.getCell(row, col).setBackground(Color.BLACK);
      }
    }

    boolean[][] visited = new boolean[gridSize][gridSize];
    Stack<Point> stack = new Stack<>();
    Random random = new Random();

    Point start = new Point(0, 0);
    stack.push(start);
    visited[start.x][start.y] = true;
    grid.getCell(start.x, start.y).setBackground(Color.WHITE);

    while (!stack.isEmpty()) {
      Point current = stack.peek();
      List<Point> neighbors = getUnvisitedNeighbors(current, gridSize, visited);
      if (neighbors.isEmpty()) {
        stack.pop();
      } else {
        Point next = neighbors.get(random.nextInt(neighbors.size()));
        removeWallBetween(grid, current, next);
        visited[next.x][next.y] = true;

        grid.getCell(next.x, next.y).setBackground(Color.WHITE);
        stack.push(next);

        grid.getGridPanel().repaint();
        pause(30);
      }
    }
  }

  public static List<Point> getUnvisitedNeighbors(Point current, int gridSize,
      boolean[][] visited) {
    List<Point> neighbors = new ArrayList<>();
    int x = current.x;
    int y = current.y;

    if (x > 1 && !visited[x - 2][y]) {
      neighbors.add(new Point(x - 2, y));  // Up
    }
    if (x < gridSize - 2 && !visited[x + 2][y]) {
      neighbors.add(new Point(x + 2, y));  // Down
    }
    if (y > 1 && !visited[x][y - 2]) {
      neighbors.add(new Point(x, y - 2));  // Left
    }
    if (y < gridSize - 2 && !visited[x][y + 2]) {
      neighbors.add(new Point(x, y + 2));  // Right
    }
    return neighbors;
  }

  public static void removeWallBetween(Grid grid, Point current, Point next) {
    int x = (current.x + next.x) / 2;
    int y = (current.y + next.y) / 2;
    grid.getCell(x, y).setBackground(Color.WHITE);
  }

  public static void pause(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}