package org.universe;

import org.universe.core.GameOfLife;
import org.universe.model.GliderPoint;
import org.universe.utils.UniverseUtils;

import java.util.List;

public class Bootstrap {

    public static void main(String[] args) {
        // Create a 25x25 board
        int rows = 25;
        int columns = 25;
        GameOfLife game = new GameOfLife(rows, columns);

        // Set initial state with a Glider pattern
        int[][] universeInitPattern = UniverseUtils.getEmptyUniverseByDimension(rows, columns);
        List<GliderPoint> gliderPoints = UniverseUtils.parseGliderPattern("glider.templates/glider_v1.csv");
        int[][] initialUniverseState = UniverseUtils.applyGliderPattern(universeInitPattern, gliderPoints);
        game.setInitialState(initialUniverseState);

        // Print initial state
        System.out.println("Initial State:");
        game.printBoard();

        // Generate and print next 10 generations
        for (int i = 1; i <= 10; i++) {
            System.out.println("Generation " + i + ":");
            game.nextGeneration();
            game.printBoard();
            System.out.println();
        }
    }
}
