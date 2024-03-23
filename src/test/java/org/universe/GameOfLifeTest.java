package org.universe;

import org.junit.jupiter.api.Test;
import org.universe.core.GameOfLife;
import org.universe.model.GliderPoint;
import org.universe.utils.UniverseUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GameOfLifeTest {

    @Test
    public void testNextGeneration() {
        // Create a 3x3 board
        GameOfLife game = new GameOfLife(3, 3);

        // Set initial state with a Blinker pattern
        int[][] blinkerPattern = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        game.setInitialState(blinkerPattern);

        // Generate next generation
        game.nextGeneration();

        // Check if the next generation matches the expected state
        int[][] expectedNextGeneration = {
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        };
        assertArrayEquals(expectedNextGeneration, game.getBoard());
    }

    @Test
    public void testInitialPatternWithGlider() {
        // Create a 5x5 board
        GameOfLife game = new GameOfLife(5, 5);

        // Set initial state with a Glider pattern
        String[] gliderPattern = {
                "0, 0, 1, 0, 0",
                "1, 0, 1, 0, 0",
                "0, 1, 1, 0, 0",
                "0, 0, 0, 0, 0",
                "0, 0, 0, 0, 0"
        };

        int[][] emptyUniverse = UniverseUtils.getEmptyUniverseByDimension(5, 5);
        List<GliderPoint> gliderPoints = UniverseUtils.parseGliderPatternFromStringArray(gliderPattern);
        final int[][] universe = UniverseUtils.applyGliderPattern(emptyUniverse, gliderPoints);
        game.setInitialState(universe);
        game.printBoard();
        // Generate next generation
        game.nextGeneration();
        System.out.println(">>>>>>>");
        game.printBoard();
        // Check if the next generation matches the expected state
        int[][] expectedNextGeneration = {
                {0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        assertArrayEquals(expectedNextGeneration, game.getBoard());
    }

}
