package org.universe.utils;

import org.universe.model.GliderPoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class UniverseUtils {

    private final static String COMMA_DELIMITER = ",";

    public static List<GliderPoint> parseGliderPattern(String templatePath) {
        Objects.requireNonNull(templatePath, "Template path must not be null");

        List<GliderPoint> gliderPoints = new ArrayList<>();
        try (InputStream res = UniverseUtils.class.getClassLoader().getResourceAsStream(templatePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(res, "UTF-8"))) {

            String line;
            int lineIndex = 0;
            while ((line = br.readLine()) != null) {
                processLine(line, gliderPoints, lineIndex);
                lineIndex++;
            }
        } catch (IOException e) {
            System.err.println("Error reading template file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Cannot process template: " + e.getMessage());
        }
        return gliderPoints;
    }

    public static List<GliderPoint> parseGliderPatternFromStringArray(String[] template) {
        Objects.requireNonNull(template, "Template array must not be null");
        List<GliderPoint> gliderPoints = new ArrayList<>();
        for (int i = 0; i < template.length; i++) {
            processLine(template[i], gliderPoints, i);
        }
        return gliderPoints;
    }

    private static void processLine(String line, List<GliderPoint> gliderPoints, int lineIndex) {
        if (line == null || line.isEmpty()) {
            return;
        }
        String[] values = line.split(COMMA_DELIMITER);
        IntStream.range(0, values.length)
                .forEach(idx -> {
                    if (isPoint(values[idx])) {
                        gliderPoints.add(GliderPoint.builder().x(lineIndex).y(idx).build());
                    }
                });
    }

    public static int[][] applyGliderPattern(int[][] universe, List<GliderPoint> gliderPoints) {
        gliderPoints.forEach(point -> {
            if (point.getX() < universe.length) {
                int[] y = universe[point.getX()];
                if (point.getY() < y.length) {
                    universe[point.getX()][point.getY()] = 1;
                }
            }
        });
        return universe;
    }

    public static int[][] getEmptyUniverseByDimension(int rows, int cols) {
        int[][] universe = new int[rows][cols];
        for (int i = 0; i < universe.length; i++) {
            int[] y = universe[i];
            for (int l = 0; l < y.length; l++) {
                universe[i][l] = 0;
            }
        }
        return universe;
    }

    private static boolean isPoint(String str) {
        // Check if the input string is null
        if (str == null) {
            System.err.println("Input string is null.");
            return false;
        }
        str = str.trim(); // Remove leading and trailing whitespaces
        try {
            return Integer.parseInt(str) == 1;
        } catch (NumberFormatException e) {
            System.err.println("Cannot parse number: " + str);
            return false;
        }
    }
}
