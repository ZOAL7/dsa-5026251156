package LW1.Unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Rental> rentals = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("rentals.txt"))) {
            while (sc.hasNext()) {
                String type = sc.next();
                String id = sc.next();
                int days = sc.nextInt();

                if (type.equals("PROJECTOR")) {
                    rentals.add(new ProjectorRental(id, days));
                } else if (type.equals("LAPTOP")) {
                    rentals.add(new LaptopRental(id, days));
                } else {
                    throw new IllegalArgumentException("Unknown rental type: " + type);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find rentals.txt in the working directory.");
            return;
        }

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }
}
