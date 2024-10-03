package com.project;

import javax.swing.SwingUtilities;

public class Main {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new PathfindingVisualizer().setVisible(true));
  }
}