package LW04.Prelab04;

import java.io.File;
import java.util.Scanner;

public class RidTimeHop {
    public static void main(String[] args) throws java.io.FileNotFoundException {
        File inputFile = new File("branches.txt");
        if (!inputFile.isFile()) {
            inputFile = new File("src", "LW04" + File.separator + "Prelab04"
                    + File.separator + "branches.txt");
        }
        if (!inputFile.isFile()) {
            inputFile = new File("LW04", "Prelab04" + File.separator + "branches.txt");
        }

        try (Scanner input = new Scanner(inputFile)) {
            int n = input.nextInt();
            int[] branches = new int[n];

            for (int i = 0; i < n; i++) {
                branches[i] = input.nextInt();
            }

            int m = input.nextInt();
            int[] targets = new int[m];

            for (int i = 0; i < m; i++) {
                targets[i] = input.nextInt();
            }

            insertionSortDescending(branches, 3);

            for (int i = 0; i < branches.length; i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(branches[i]);
            }
            System.out.println();

            for (int target : targets) {
                int index = linearSearch(branches, target);
                if (index >= 0) {
                    System.out.println(target + ": FOUND at index " + index);
                } else {
                    System.out.println(target + ": NOT FOUND");
                }
            }
        }
    }

    private static void insertionSortDescending(int[] values, int passes) {
        for (int i = 1; i <= passes && i < values.length; i++) {
            int key = values[i];
            int j = i - 1;

            while (j >= 0 && values[j] < key) {
                values[j + 1] = values[j];
                j--;
            }

            values[j + 1] = key;
        }
    }

    private static int linearSearch(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
