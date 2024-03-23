package org.universe.core;

import lombok.Getter;
import lombok.SneakyThrows;

public class GameOfLife {

    @Getter
    private int[][] board;
    private final int rows;
    private final int cols;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];
    }

    public void setInitialState(int[][] initialState) {
        if (initialState.length != rows || initialState[0].length != cols) {
            throw new IllegalArgumentException("Initial state size does not match the board size");
        }
        this.board = initialState;
    }

    public void nextGeneration() {
        int[][] newBoard = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int aliveNeighbors = countAliveNeighbors(i, j);

                if (board[i][j] == 1) {
                    if (aliveNeighbors < 2 || aliveNeighbors > 3) {
                        newBoard[i][j] = 0; // cell dies
                    } else {
                        newBoard[i][j] = 1; // cell lives on
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        newBoard[i][j] = 1; // cell becomes alive
                    }
                }
            }
        }
        board = newBoard;
    }

    private int countAliveNeighbors(int row, int col) {
        int count = 0;
        int[][] neighbors = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int[] neighbor : neighbors) {
            int r = row + neighbor[0];
            int c = col + neighbor[1];

            if (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == 1) {
                count++;
            }
        }
        return count;
    }

    @SneakyThrows
    public void printBoard() {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "\u001b[32m[X]\u001b[0m" : "\u001b[31m _ \u001b[0m");
            }
            System.out.println();
        }
    }
}
