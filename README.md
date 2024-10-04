# Pathfinding-Visualizer

**Pathfinding-Visualizer** is a Java-based application that visualizes various pathfinding algorithms, including DFS, BFS, Dijkstra's, and A* Search. This tool helps users understand how different algorithms navigate mazes and grids in real time. Note that the time taken is not the actual time complexity but rather an approximation for educational and comparative purposes.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Color Legend](#color-legend)
- [Algorithms](#algorithms)
- [Usage](#usage)
    - [Running the Visualizer](#running-the-visualizer)
    - [Generating Mazes](#generating-mazes)
    - [Choosing Algorithms](#choosing-algorithms)
- [Screenshots](#screenshots)
- [Demo](#demo)

## Introduction

Pathfinding-Visualizer is an educational and interactive tool that visualizes popular pathfinding algorithms. It allows users to select and compare different algorithms on a grid to see how each finds the shortest path between points.

## Features

- Supports **DFS**, **BFS**, **Dijkstra's Algorithm**, and **A* Search**.
- Visual indicators for **start** and **end points**, **visited nodes**, **optimal path**, and **nodes currently being explored**.
- Scoreboard to compare **execution times** between different algorithms.
- Maze generation options to create random or structured mazes.
- Clean, interactive GUI with dynamic grid editing.

## Color Legend

To enhance understanding of the algorithm’s path, different colors are used to represent different states of the nodes:

| **Color** | **Meaning**               |
|-----------|---------------------------|
| **Red**   | Start Node                |
| **Yellow**| End Node                  |
| **White** | Unexplored/Empty Node     |
| **Black** | Wall/Blocked Node         |
| **Green** | Nodes to be Explored      |
| **Blue**  | Visited Nodes             |
| **Pink**  | Optimal Path              |

## Algorithms

### Implemented Algorithms
1. **Breadth-First Search (BFS)**:
    - Explores all possible nodes evenly in all directions until it finds the end.
    - Ideal for unweighted grids.

2. **Depth-First Search (DFS)**:
    - Explores deeply in one direction before backtracking.
    - Not guaranteed to find the shortest path.

3. **Dijkstra's Algorithm**:
    - Expands nodes based on the shortest path using a priority queue.
    - Finds the shortest path in weighted grids.

4. **A* Search**:
    - Uses heuristics (Euclidean or Manhattan distance) to prioritize nodes, improving efficiency.
    - Generally faster than Dijkstra’s for most cases.

## Usage

### Running the Visualizer

1. **Set the Start and End Points**: Right-click the grid to place checkpoints.
2. **Create Walls**: Use left-click to drag and drop walls to form a maze.
3. **Choose an Algorithm**: Click on one of the algorithm buttons to start visualizing.
4. **Use the Scoreboard**: Compare and analyze execution times.

### Generating Mazes
- **Generate Maze**: Click the `Generate Maze` button for a random maze layout.
- **Reset Paths**: Clears the visualized paths but retains the maze structure for further experimentation.

### Choosing Algorithms
- **Switch Algorithms**: Retain the same maze layout to test different algorithms without clearing the board.
- **Track Time**: Use the scoreboard to view and compare execution times between algorithms for a better understanding of efficiency.

## Demo

### GIF Demonstrations:
- [DFS Pathfinding](docs/dfs_demo.gif)
- [BFS Pathfinding](docs/bfs_demo.gif)
- [Dijkstra’s Algorithm](docs/dijkstra_demo.gif)
- [A* Search](docs/astar_demo.gif)
