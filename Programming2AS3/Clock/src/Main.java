import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Maintains the current date and time.
 * 
 * @author Prason KC
 */
class Clock {
    /**
     * Stores the most recently captured date and time value.
     */
    private volatile LocalDateTime dateTime;

    /**
     * Creates a clock initialized with the current local date and time.
     */
    Clock() {
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Displays the stored date and time in the console.
     */
    void displayDateTime() {
        // Formats the time in HH:mm:ss dd-MM-yyyy format.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        System.out.print("\r Current Time and Date: " + "[" + dateTime.format(formatter) + "]");
    }

    /**
     * Updates the date and time variable.
     */
    void updateDateTime() {
        dateTime = LocalDateTime.now();
    }
}

/**
 * Starts the clock application.
 *
 * @author Prason KC
 */
class Main {
    /**
     * Launches the clock application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        /**
         * Coordinates access to the current date and time value.
         */
        Clock clock = new Clock();

        /**
         * Refreshes the stored time every 200 ms.
         */
        Thread updateThread = new Thread(() -> {
            while (true) {
                try {
                    clock.updateDateTime();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        /**
         * Prints the current time to the console every second.
         */
        Thread displayThread = new Thread(() -> {
            while (true) {
                try {
                    clock.displayDateTime();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        updateThread.setPriority(Thread.MIN_PRIORITY);
        displayThread.setPriority(Thread.MAX_PRIORITY);

        updateThread.start();
        displayThread.start();
    }
}
