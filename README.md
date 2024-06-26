<h1>Game of Life task</h1>

### Definition:

The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbors, which are the cells that are horizontally, vertically, or diagonally adjacent.
Rules
At each step in time, the following transitions occur:
1. Any live cell with fewer than two live neighbors dies as if caused by underpopulation.
2. Any live cell with two or three live neighbors lives on to the next generation.
3. Any live cell with more than three live neighbors dies, as if by overcrowding.
4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
   The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed—births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). The rules continue to be applied repeatedly to create further generations.
   
### Objectives
1. Implement the game of life data structures and algorithm.
2. Demonstrate that game of life algorithm works.
   
### Hints
- To demonstrate that the program works you can print out the state of the universe to the console/output after each generation. There is no need to build a custom UI.
- The program must run and work properly (the working program is better than in-progress design). 
- Use the ‘Glider’ pattern placed in the middle of the 25x25 cell universe for demonstration.

### App Demo
![Proof 1](./src/test/resources/Screenshot%202024-03-23%20at%2021.06.31.png)
![Proof 2](./src/test/resources/Screenshot%202024-03-23%20at%2021.40.38.png)
