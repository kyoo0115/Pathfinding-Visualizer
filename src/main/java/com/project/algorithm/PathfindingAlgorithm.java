package com.project.algorithm;

import java.awt.Point;
import java.util.Map;
import javax.swing.JPanel;

public interface PathfindingAlgorithm {

  void runAlgorithm(JPanel[][] grid, int startX, int startY, int endX, int endY,
      Map<Point, Point> checkpoints);
}