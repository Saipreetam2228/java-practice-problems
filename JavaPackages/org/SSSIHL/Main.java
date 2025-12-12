package org.sssihl;

import java.util.Scanner;
import org.sssihl.generator.AutoGenerator;
import org.sssihl.solution.Solution;

public class Main {
    public static void main(String... arguments) {
        Scanner s = new Scanner(System.in);
        System.out.print("Size: ");
        String sizeStr = s.nextLine();
        System.out.print("Start: ");
        String startStr = s.nextLine();
        System.out.print("Goal: ");
        String goalStr = s.nextLine();

        String[] sizeArr = sizeStr.split("x");
        String[] startArr = startStr.split(",");
        String[] goalArr = goalStr.split(",");
        
        int[] size = {Integer.parseInt(sizeArr[0]),Integer.parseInt(sizeArr[1])};
        int[] start = {Integer.parseInt(startArr[0]), Integer.parseInt(startArr[1])};
        int[] end = {Integer.parseInt(goalArr[0]),Integer.parseInt(goalArr[1])};

        System.out.println("You can see the Grid Below...");
        int[][] grid = new AutoGenerator().generate2DArray(size, start, end);
        for (int[] row : grid) {
            for (int c : row) System.out.print(c + " ");
            System.out.println();
        }

        Solution solution = new Solution();
        int[][] path = solution.run(start, end, grid);
        System.out.print("Path: ");
        print(path);
    }

    public static void print(int[][] coords) {
    for (int i = 0; i < coords.length; i++) {
        System.out.print("(" + coords[i][0] + "," + coords[i][1] + ")");
        if (i < coords.length - 1) System.out.print(", ");
    }
    System.out.println();
}
}

/*

*/
