/**
 * Represents a generic library item with a title, author, and item ID.
 *
 * @param <T> the data type used for the item ID
 * @author Prason KC
 */
public class LibraryItem<T> {
    /** The title of the library item. */
    private final String title;

    /** The author of the library item. */
    private final String author;

    /** The unique ID used to identify the library item. */
    private final T itemID;

    /**
     * Creates a library item with the given details.
     * 
     * @param title  the title of the item
     * @param author the author or creator of the item
     * @param itemID the unique ID for the item
     */
    public LibraryItem(String title, String author, T itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    /**
     * Returns the title of this item.
     *
     * @return the item title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of this item.
     *
     * @return the item author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the ID of this item.
     *
     * @return the item ID
     */
    public T getItemID() {
        return itemID;
    }

    /**
     * Displays the details of this item.
     */
    public void displayItem() {
        System.out.println();
        System.out.println("Id: " + itemID);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("[ID: %s] Title: %s | Author: %s", itemID, title, author);
    }
}
