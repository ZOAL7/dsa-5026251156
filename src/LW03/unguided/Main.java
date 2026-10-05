package LW03.unguided;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {

    private static File findInputFile(String fileName) throws Exception {
        String[] candidates = {
            fileName,
            "LW03/unguided/" + fileName,
            "src/LW03/unguided/" + fileName,
            "bin/LW03/unguided/" + fileName
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
        LinkedHashMap<String, Integer> enrollment = new LinkedHashMap<>();
        ArrayList<String> courseOrder = new ArrayList<>();
        ArrayList<String> checkResults = new ArrayList<>();
        int rejectedOperations = 0;

        try (Scanner sc = new Scanner(findInputFile("enrollment.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\s+");
                String operation = data[0];

                if (data.length < 2) {
                    rejectedOperations++;
                    continue;
                }

                String courseCode = data[1];

                if (operation.equals("REGISTER") || operation.equals("WITHDRAW")) {
                    if (data.length < 3) {
                        rejectedOperations++;
                        continue;
                    }

                    int count = Integer.parseInt(data[2]);

                    if (count <= 0) {
                        rejectedOperations++;
                        continue;
                    }

                    if (operation.equals("REGISTER")) {
                        if (!enrollment.containsKey(courseCode)) {
                            enrollment.put(courseCode, count);
                            courseOrder.add(courseCode);
                        } else {
                            enrollment.put(
                                courseCode,
                                enrollment.get(courseCode) + count
                            );
                        }
                    } else {
                        if (enrollment.containsKey(courseCode)) {
                            int current = enrollment.get(courseCode);
                            if (current >= count) {
                                enrollment.put(courseCode, current - count);
                            } else {
                                rejectedOperations++;
                            }
                        } else {
                            rejectedOperations++;
                        }
                    }

                } else if (operation.equals("CHECK")) {
                    if (enrollment.containsKey(courseCode)) {
                        int current = enrollment.get(courseCode);
                        checkResults.add(courseCode + ": " + current + " students");
                    } else {
                        checkResults.add(courseCode + ": Not Found");
                    }

                } else {
                    rejectedOperations++;
                }
            }
        }

        System.out.println("===== Enrollment Checks =====");
        for (String result : checkResults) {
            System.out.println(result);
        }

        System.out.println("===== Final Enrollment =====");
        for (String code : courseOrder) {
            System.out.println(code + ": " + enrollment.get(code) + " students");
        }

        System.out.println("Rejected operations: " + rejectedOperations);
    }
}
