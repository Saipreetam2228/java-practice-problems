package org.sssihl.generator;

public class AutoGeneratorMain {
    public static void main(String[] args) {
        int[] size = {5,5}, start = {0,0}, end = {4,4};
        int[][] grid = new AutoGenerator().generate2DArray(size, start, end);
        for (int[] row : grid) {
            for (int c : row) System.out.print(c + " ");
            System.out.println();
        }
    }
}