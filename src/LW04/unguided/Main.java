package LW04.unguided;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static File findInputFile(String fileName) throws Exception {
        String[] candidates = {
            fileName,
            "src/LW04/unguided/" + fileName,
            "LW04/unguided/" + fileName,
            "bin/LW04/unguided/" + fileName
        };

        for (String candidate : candidates) {
            File file = new File(candidate);
            if (file.isFile()) {
                return file;
            }
        }

        throw new Exception("Could not find " + fileName);
    }

    static void selectionSort(int[] arr, int passes) {
        int n = arr.length;
        for (int i = 0; i < passes && i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"))) {
            int n = sc.nextInt();
            int[] cards = new int[n];
            for (int i = 0; i < n; i++) {
                cards[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            int[] requests = new int[m];
            for (int i = 0; i < m; i++) {
                requests[i] = sc.nextInt();
            }

            selectionSort(cards, 2);

            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print(cards[i]);
            }
            System.out.println();

            for (int i = 0; i < m; i++) {
                int index = linearSearch(cards, requests[i]);
                if (index >= 0) {
                    System.out.println(requests[i] + ": FOUND at index " + index);
                } else {
                    System.out.println(requests[i] + ": NOT FOUND");
                }
            }
        }
    }
}
