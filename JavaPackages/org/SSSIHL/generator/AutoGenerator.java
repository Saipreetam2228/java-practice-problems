package org.sssihl.generator;

import java.util.Random;
import org.sssihl.solution.Solution;

public class AutoGenerator {
    public int[][] generate2DArray(int[] size, int[] start, int[] end) {
        int[][] grid = new int[size[0]][size[1]];
        Random r = new Random(Solution.REGNUMBER);
        for (int i = 0; i < size[0]; i++)
            for (int j = 0; j < size[1]; j++)
                grid[i][j] = r.nextInt(4) == 0 ? 1 : 0;
        grid[start[0]][start[1]] = 0;
        grid[end[0]][end[1]] = 2;
        return grid;
    }
}