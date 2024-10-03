# Pathfinding-Visualizer

**Pathfinding-Visualizer** is a Java-based application that visualizes various pathfinding algorithms, including DFS, BFS, Dijkstra's, and A* Search. This tool helps users understand how different algorithms navigate mazes and grids in real time. Note that the time taken is not the actual time complexity but rather an approximation for educational and comparative purposes.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Algorithms](#algorithms)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
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

## Algorithms

### Implemented Algorithms:
1. **Breadth-First Search (BFS)**: Expands evenly in all directions.
2. **Depth-First Search (DFS)**: Explores paths deeply before backtracking.
3. **Dijkstra's Algorithm**: Expands based on the shortest path using a priority queue.
4. **A* Search**: Uses heuristics to find the most optimal path quickly.

## Getting Started

### Prerequisites

- **Java JDK 8 or above** installed.
- **Maven** or other build tools (optional).

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/YourUsername/Pathfinding-Visualizer.git
    ```

2. Navigate to the project directory:
    ```bash
    cd Pathfinding-Visualizer
    ```

3. Compile the project:
    ```bash
    javac -d bin src/**/*.java
    ```

4. Run the visualizer:
    ```bash
    java -cp bin com.project.PathfindingVisualizer
    ```

## Usage

### Running the Visualizer

1. Start the application using the command:
    ```bash
    java -cp bin com.project.PathfindingVisualizer
    ```

2. Interact with the GUI:
   - **Set the start and end points** by right-clicking the grid.
   - **Create walls** by left-clicking and dragging on the grid.
   - **Generate a maze** using the `Generate Maze` button.
   - **Run an algorithm** using the respective buttons (DFS, BFS, Dijkstra, A*).

### Generating Mazes
- Use the `Generate Maze` button to create a random maze.
- Use `Reset Paths` to clear paths while retaining the maze structure.

### Choosing Algorithms
- Click on any of the algorithm buttons to start visualizing.
- Use the `Scoreboard` to view and compare execution times.

## Screenshots

### Pathfinding with BFS
![BFS Visualization](docs/bfs_demo.gif)

### Pathfinding with A*
![A* Visualization](docs/astar_demo.gif)

## Demo

### GIF Demonstrations:
- [DFS Pathfinding](docs/dfs_demo.gif)
- [BFS Pathfinding](docs/bfs_demo.gif)
- [Dijkstra’s Algorithm](docs/dijkstra_demo.gif)
- [A* Search](docs/astar_demo.gif)
