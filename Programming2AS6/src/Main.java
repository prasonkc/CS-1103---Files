import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A simple command-line interface for the generic library catalog.
 *
 * @author Prason KC
 */
public class Main {
    /**
     * Starts the library catalog program.
     *
     * @param args command-line argument
     */
    public static void main(String[] args) {
        // Reads user input for the catalog menu.
        Scanner scanner = new Scanner(System.in);

        // Stores the library items.
        LibraryCatalog<Integer, LibraryItem<Integer>> catalog = new LibraryCatalog<>();
        boolean running = true;

        while (running) {
            displayMenu();
            running = handleInput(scanner, catalog);
        }

        scanner.close();
    }

    /**
     * Displays the menu options for the user.
     */
    public static void displayMenu() {
        System.out.println("\n--- Library Catalog Menu ---");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. View Catalog");
        System.out.println("4. Exit");
        System.out.println("----------------------------------------");
        System.out.print("Choose an option: ");
    }

    /**
     * Processes one menu selection from the user.
     * 
     * @param scanner reads user input from the console
     * @param catalog stores the library items
     * @return {@code true} to continue the program, or {@code false} to exit
     */
    public static boolean handleInput(
            Scanner scanner,
            LibraryCatalog<Integer, LibraryItem<Integer>> catalog) {
        try {
            int menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    addItem(scanner, catalog);
                    break;
                case 2:
                    removeItem(scanner, catalog);
                    break;
                case 3:
                    catalog.displayCatalog();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    return false;
                default:
                    System.out.println("Invalid input. Please choose a number between 1-4.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers only.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    /**
     * Reads the information for a new library item and adds it to the catalog.
     * 
     * @param scanner reads user input from the console
     * @param catalog stores the library items
     */
    private static void addItem(
            Scanner scanner,
            LibraryCatalog<Integer, LibraryItem<Integer>> catalog) {
        System.out.print("Enter item type (book/dvd): ");
        String itemType = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter item ID: ");
        int itemId = scanner.nextInt();
        scanner.nextLine();

        LibraryItem<Integer> item;

        if (itemType.equals("book")) {
            System.out.print("Enter page count: ");
            int pageCount = scanner.nextInt();
            scanner.nextLine();
            item = new Book<>(title, author, itemId, pageCount);
        } else if (itemType.equals("dvd")) {
            System.out.print("Enter duration in minutes: ");
            double duration = scanner.nextDouble();
            scanner.nextLine();
            item = new DVD<>(title, author, itemId, duration);
        } else {
            throw new IllegalArgumentException("Invalid item type. Please enter either book or dvd.");
        }

        catalog.addItem(item);
    }

    /**
     * Reads an item ID from the user and removes that item from the catalog.
     * 
     * @param scanner reads user input from the console
     * @param catalog stores the library items
     */
    private static void removeItem(
            Scanner scanner,
            LibraryCatalog<Integer, LibraryItem<Integer>> catalog) {
        System.out.print("Enter item ID to remove: ");
        int itemId = scanner.nextInt();
        scanner.nextLine();

        catalog.removeItem(itemId);
    }
}
