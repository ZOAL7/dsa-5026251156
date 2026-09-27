import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BankTransactionProcessing {

    public static void main(String[] args) throws Exception {

        LinkedList<String[]> transactions = new LinkedList<>();

        LinkedList<String[]> customers = new LinkedList<>();

        try (Scanner scanner = new Scanner(new File("transactions.txt"))) {
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\s+");
                if (data.length != 3) {
                    throw new IllegalArgumentException(
                        "Format transaksi tidak valid pada baris " + lineNumber
                            + ". Gunakan: nama jenis jumlah"
                    );
                }

                String name = data[0];
                String type = data[1];
                int amount;
                try {
                    amount = Integer.parseInt(data[2]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                        "Jumlah transaksi tidak valid pada baris " + lineNumber
                            + ": " + data[2],
                        e
                    );
                }

                if (amount < 0) {
                    throw new IllegalArgumentException(
                        "Jumlah transaksi tidak boleh negatif pada baris " + lineNumber
                    );
                }
                if (!type.equals("DEPOSIT") && !type.equals("WITHDRAW")) {
                    throw new IllegalArgumentException(
                        "Jenis transaksi tidak dikenal pada baris " + lineNumber
                            + ": " + type
                    );
                }

                transactions.add(new String[]{name, type, String.valueOf(amount)});

                boolean found = false;
                for (String[] customer : customers) {
                    if (customer[0].equals(name)) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    customers.add(new String[]{name, "0"});
                }
            }
        }

        Queue<String[]> queue = new LinkedList<>();

        while (!transactions.isEmpty()) {
            queue.add(transactions.removeFirst());
        }

        System.out.println("Transaction");
        System.out.println("Balance after processing");
        System.out.println();

        while (!queue.isEmpty()) {

            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            for (String[] customer : customers) {

                if (customer[0].equals(name)) {

                    int balance = Integer.parseInt(customer[1]);

                    System.out.println(name + " " + type + " " + amount);

                    if (type.equals("DEPOSIT")) {

                        balance += amount;
                        customer[1] = String.valueOf(balance);
                        System.out.println(name + " = " + balance);

                    } else {

                        if (amount <= balance) {

                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                            System.out.println(name + " = " + balance);

                        } else {

                            System.out.println(
                                "Failed, " + name + " remains " + balance
                            );
                        }
                    }

                    if (!queue.isEmpty()) {
                        System.out.println();
                    }

                    break;
                }
            }
        }
    }
}