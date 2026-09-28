package LW02.unguided;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class LibraryBookBorrowing {
    public static void main(String[] args) throws Exception {
        final int MAX_BORROW = 2;
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();
        LinkedList<String[]> successful = new LinkedList<>();

        File inputFile = new File(args.length > 0 ? args[0] : "src/LW02/unguided/borrowing.txt");
        try (Scanner file = new Scanner(inputFile)) {
            while (file.hasNextLine()) {
                String line = file.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                try (Scanner lineScanner = new Scanner(line)) {
                    if (!lineScanner.hasNext()) {
                        continue;
                    }
                    String name = lineScanner.next();
                    if (!lineScanner.hasNext()) {
                        continue;
                    }
                    String book = lineScanner.next();
                    requests.add(new String[]{name, book});

                    boolean memberExists = false;
                    for (String[] member : members) {
                        if (member[0].equals(name)) {
                            memberExists = true;
                            break;
                        }
                    }
                    if (!memberExists) {
                        members.add(new String[]{name, "0"});
                    }
                }
            }
        }

        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        while (!requests.isEmpty()) {
            queue.add(requests.remove());
        }
        while (!queue.isEmpty()) {
            String[] request = queue.poll();
            String name = request[0];
            String bookName = request[1];
            int bookIndex = -1;
            int memberIndex = -1;

            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(bookName)) {
                    bookIndex = i;
                    break;
                }
            }
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i)[0].equals(name)) {
                    memberIndex = i;
                    break;
                }
            }

            if (bookIndex >= 0 && memberIndex >= 0) {
                int stock = Integer.parseInt(books.get(bookIndex)[1]);
                int borrowed = Integer.parseInt(members.get(memberIndex)[1]);

                if (stock > 0 && borrowed < MAX_BORROW) {
                    successful.add(request);
                    stock--;
                    books.get(bookIndex)[1] = String.valueOf(stock);
                    borrowed++;
                    members.get(memberIndex)[1] = String.valueOf(borrowed);
                    continue;
                }
            }

            failed.push(request);
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] request : successful) {
            System.out.println(request[0] + " " + request[1]);
        }
        System.out.println();
        System.out.println("=== Remaining Book Stock ===");
        for (String[] book : books) {
            System.out.println(book[0] + " : " + book[1]);
        }
        System.out.println();
        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] request = failed.pop();
            System.out.println(request[0] + " " + request[1]);
        }
    }
}