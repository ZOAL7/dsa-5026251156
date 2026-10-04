package LW03.Prelab03;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static File findInputFile(String fileName) throws Exception {
        String[] candidates = {
            fileName,
            "LW03/Prelab03/" + fileName,
            "src/LW03/Prelab03/" + fileName,
            "bin/LW03/Prelab03/" + fileName
        };

        for (String candidate : candidates) {
            File file = new File(candidate);
            if (file.isFile()) {
                return file;
            }
        }

        throw new Exception("Could not find " + fileName);
    }

    public static void main(String[] args) throws Exception {

        System.out.println("===== Problem 1 =====");

        List<String> playlist = new LinkedList<>();

        try (Scanner sc = new Scanner(findInputFile("playlist.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\s+", 3);

                if (data[0].equals("ADD")) {
                    playlist.add(data[1]);

                } else if (data[0].equals("INSERT")) {
                    int index = Integer.parseInt(data[1]);
                    playlist.add(index, data[2]);

                } else if (data[0].equals("REMOVE")) {
                    playlist.remove(data[1]);
                }
            }
        }

        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println("===== Problem 2 =====");

        LinkedHashSet<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        try (Scanner sc2 = new Scanner(findInputFile("participants.txt"))) {
            while (sc2.hasNextLine()) {
                String name = sc2.nextLine().trim();
                if (name.isEmpty()) {
                    continue;
                }

                if (participants.contains(name)) {
                    duplicateRegistrations++;
                } else {
                    participants.add(name);
                }
            }
        }

        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println(
            "Duplicate registrations: " + duplicateRegistrations
        );

        System.out.println("===== Problem 3 =====");

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try (Scanner sc3 = new Scanner(findInputFile("inventory.txt"))) {
            while (sc3.hasNextLine()) {
                String line = sc3.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\s+");

                String type = data[0];
                String product = data[1];
                int quantity = Integer.parseInt(data[2]);

                if (type.equals("ADD")) {

                    if (inventory.containsKey(product)) {
                        int currentStock = inventory.get(product);
                        inventory.put(product, currentStock + quantity);
                    } else {
                        inventory.put(product, quantity);
                    }

                } else if (type.equals("SELL")) {

                    if (inventory.containsKey(product)
                            && inventory.get(product) >= quantity) {

                        int currentStock = inventory.get(product);
                        inventory.put(product, currentStock - quantity);

                    } else {
                        failedSales++;
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(
                entry.getKey() + ": " + entry.getValue()
            );
        }

        System.out.println("Failed sales: " + failedSales);
    }
}