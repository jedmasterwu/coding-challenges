package com.wx.coding.tests;

import edu.princeton.cs.algs4.Stopwatch;
import org.testng.annotations.Test;
import com.wx.courses.algs4.Percolation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PercolationTest {

    @Test
    public void testPercolation() {
        File folder = new File("/Users/wuming/Downloads/percolation/");
        File[] txtFiles = folder.listFiles((dir, name) -> name.endsWith("txt"));

        Stopwatch stopwatch = new Stopwatch();
        for (File file : txtFiles) {
            System.out.println("===================== " + file.getAbsolutePath() + " =====================");
            try {
                Scanner scanner = new Scanner(file);
                Percolation percolation = new Percolation(scanner.nextInt());
                int i = 0;
                while (scanner.hasNextInt()) {
                    try {
                        int row = scanner.nextInt();
                        int col = scanner.nextInt();
                        i++;
                        percolation.open(row, col);
                        System.out.println("Call #" + i + ": percolates() returns " + percolation.percolates());
                        System.out.println("Call #" + i + ": isFull() returns " + percolation.isFull(row, col));
                    } catch (NoSuchElementException e) {
                        break;
                    }
                }

                System.out.println(file + " percolates: " + percolation.percolates());
            } catch (FileNotFoundException e) {
                // Do nothing;
            }
        }

        System.out.println("Total time is: " + stopwatch.elapsedTime() + " seconds");
    }
}
