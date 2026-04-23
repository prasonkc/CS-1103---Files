/**
 * Represents a book item in the library catalog.
 *
 * @param <T> the data type used for the item ID
 * @author Prason KC
 */
public class Book<T> extends LibraryItem<T> {
    /** The number of pages in the book. */
    private final int pageCount;

    /**
     * Creates a book with the given details.
     *
     * @param title     the title of the book
     * @param author    the author of the book
     * @param itemID    the unique ID for the book
     * @param pageCount the total number of pages in the book
     */
    public Book(String title, String author, T itemID, int pageCount) {
        super(title, author, itemID);
        this.pageCount = pageCount;
    }

    /**
     * Displays the details of this book.
     */
    @Override
    public void displayItem() {
        super.displayItem();
        System.out.println("Type: Book | Pages: " + pageCount);
    }
}
