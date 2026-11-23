package LW06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));
        BinarySearchTree bst = new BinarySearchTree();

        Scanner numbers = new Scanner(read.nextLine());
        while (numbers.hasNextInt()) {
            bst.insert(numbers.nextInt());
        }

        int target = read.nextInt();

        System.out.print("Preorder : ");
        bst.preOrderHelper(bst.root);
        System.out.println();

        System.out.print("Inorder : ");
        bst.inOrderHelper(bst.root);
        System.out.println();

        System.out.print("Postorder : ");
        bst.postOrderHelper(bst.root);
        System.out.println();

        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Minimum : " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());
    }
}