package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            int choice = menu(input);
            if (choice == 5)
                break;
            functions(choice, input, library);
        }
    }

    private static int menu(Scanner input) {
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("1. Add Book\n2. Search Book\n3. Display All Books\n4. Update Availability\n5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = input.nextInt();

                if (choice >= 1 && choice <= 5) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
        return choice;
    }

    private static void addBookFunction(Scanner input, Library library) {
        System.out.print("Set a title: ");
        String title = input.next();
        System.out.print("Set an author: ");
        String author = input.next();
        System.out.print("Set an isbn: ");

        try {
            int isbn = input.nextInt();
            library.addBook(title, author, isbn, true);
            System.out.println("Book added!");
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a valid ISBN.");
            input.nextLine();
        }
    }

    private static void searchBookFunction(Scanner input, Library library) {
        System.out.print("Type title or author: ");
        String keyword = input.next();
        List<Book> search = library.searchBook(keyword);

        if (search.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            search.forEach(System.out::println);
        }
    }

    private static void displayAllFunction(Library library) {
        List<Book> books = library.displayAll();
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void updateAvailabilityFunction(Scanner input, Library library) {
        System.out.print("Enter ISBN to update availability: ");
        try {
            int isbn = input.nextInt();
            library.updateAvailability(isbn);
            System.out.println("Availability updated successfully!");
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a valid ISBN.");
            input.nextLine();
        }
    }

    private static void functions(int choice, Scanner input, Library library) {
        switch (choice) {
            case 1:
                addBookFunction(input, library);
                break;
            case 2:
                searchBookFunction(input, library);
                break;
            case 3:
                displayAllFunction(library);
                break;
            case 4:
                updateAvailabilityFunction(input, library);
                break;
            default:
                System.out.println("Wrong input!\n");
                break;
        }
    }
}