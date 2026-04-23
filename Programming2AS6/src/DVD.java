/**
 * Represents a DVD item in the library catalog.
 *
 * @param <T> the data type used for the item ID
 * @author Prason KC
 */
public class DVD<T> extends LibraryItem<T> {
    /** The duration of the DVD in minutes. */
    private final double duration;

    /**
     * Creates a DVD with the given details.
     *
     * @param title    the title of the DVD
     * @param author   the creator or director of the DVD
     * @param itemID   the unique ID for the DVD
     * @param duration the running time of the DVD in minutes
     */
    public DVD(String title, String author, T itemID, double duration) {
        super(title, author, itemID);
        this.duration = duration;
    }

    /**
     * Displays the details of this DVD.
     */
    @Override
    public void displayItem() {
        super.displayItem();
        System.out.println("Type: DVD | Duration: " + duration + " mins");
    }
}
