import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Stores and manages a collection of generic library items.
 *
 * @param <ID> the data type used for item IDs
 * @param <T>  the type of library item stored in the catalog
 * @author Prason KC
 */
public class LibraryCatalog<ID, T extends LibraryItem<ID>> {
    /** Holds all items currently stored in the catalog. */
    private final List<T> items;

    /**
     * Creates an empty library catalog.
     */
    public LibraryCatalog() {
        items = new ArrayList<>();
    }

    /**
     * Adds a new item to the catalog.
     * 
     * @param item the item to add to the catalog
     * @throws IllegalArgumentException if another item already uses the same ID
     */
    public void addItem(T item) {
        for (T existingItem : items) {
            if (existingItem.getItemID().equals(item.getItemID())) {
                throw new IllegalArgumentException("Error: Item with same id already exists");
            }
        }

        items.add(item);
        System.out.println("Successfully added item " + item.getItemID());
    }

    /**
     * Removes the item with the given ID from the catalog.
     * 
     * @param itemID the ID of the item to remove
     * @throws IllegalArgumentException if no matching item is found
     */
    public void removeItem(ID itemID) {
        Iterator<T> iterator = items.iterator();

        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item.getItemID().equals(itemID)) {
                iterator.remove();
                System.out.println("Successfully removed item " + itemID);
                return;
            }
        }

        throw new IllegalArgumentException("Error: Item to remove was not found");
    }

    /**
     * Retrieves the item with the given ID.
     * 
     * @param itemID the ID of the item to retrieve
     * @return the matching library item
     * @throws IllegalArgumentException if no matching item is found
     */
    public T retrieveItem(ID itemID) {
        for (T item : items) {
            if (item.getItemID().equals(itemID)) {
                return item;
            }
        }

        throw new IllegalArgumentException("Error: Item not found.");
    }

    /**
     * Displays all items currently stored in the catalog.
     */
    public void displayCatalog() {
        if (items.isEmpty()) {
            System.out.println("Catalog is empty.");
            return;
        }

        for (T item : items) {
            item.displayItem();
        }
    }
}
