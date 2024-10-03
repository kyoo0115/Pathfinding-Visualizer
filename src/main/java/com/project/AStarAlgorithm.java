package com.project;

import java.awt.Color;
import java.awt.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import javax.swing.JPanel;

public class AStarAlgorithm implements PathfindingAlgorithm {

  @Override
  public void runAlgorithm(JPanel[][] grid, int startX, int startY, int endX, int endY,
      Map<Point, Point> checkpoints) {
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    Map<Point, Point> parentMap = new HashMap<>();
    PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));

    Node startNode = new Node(startX, startY, 0, calculateHeuristic(startX, startY, endX, endY));
    openList.add(startNode);
    parentMap.put(new Point(startX, startY), null);

    while (!openList.isEmpty()) {
      Node current = openList.poll();
      int x = current.x;
      int y = current.y;

        if (isOutOfBoundOrVisited(grid, x, y, visited)) {
            continue;
        }

      visited[x][y] = true;

      if (x == endX && y == endY) {
        grid[x][y].setBackground(Color.YELLOW);
        break;
      }

      for (Point dir : new Point[]{new Point(1, 0), new Point(-1, 0), new Point(0, 1),
          new Point(0, -1)}) {
        int newX = x + dir.x, newY = y + dir.y;
        if (isValidMove(newX, newY, visited, grid)) {
          Node neighbor = new Node(newX, newY, current.g + 1,
              calculateHeuristic(newX, newY, endX, endY));
          openList.add(neighbor);
          parentMap.put(new Point(newX, newY), new Point(x, y));

          if (isPathNode(grid, newX, newY)) {
            grid[newX][newY].setBackground(Color.GREEN);  // Available nodes
          }
        }
      }

        if (isPathNode(grid, x, y)) {
            grid[x][y].setBackground(Color.BLUE);  // Visited nodes
        }
      pause();
    }

    markFinalPath(grid, new Point(endX, endY), parentMap);
  }

  // Heuristic: Euclidean distance
  private int calculateHeuristic(int x, int y, int endX, int endY) {
    return Math.abs(x - endX) + Math.abs(y - endY);
  }

  private void markFinalPath(JPanel[][] grid, Point endPoint, Map<Point, Point> parentMap) {
    Point current = endPoint;
    while (current != null && parentMap.containsKey(current)) {
      if (!current.equals(parentMap.get(current)) && !isStartOrEndNode(grid, current)) {
        grid[current.x][current.y].setBackground(Color.PINK);  // Mark the final path
      }
      current = parentMap.get(current);
    }
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

  private boolean isStartOrEndNode(JPanel[][] grid, Point node) {
    Color cellColor = grid[node.x][node.y].getBackground();
    return cellColor.equals(Color.RED) || cellColor.equals(Color.YELLOW);
  }

  private static class Node {

    int x, y, g, h, f;

    Node(int x, int y, int g, int h) {
      this.x = x;
      this.y = y;
      this.g = g;
      this.h = h;
      this.f = g + h;
    }
  }
}