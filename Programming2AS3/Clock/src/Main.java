import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Clock{
    private volatile LocalDateTime dateTime;

    Clock(){
        this.dateTime = LocalDateTime.now();
    }

    void displayDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        System.out.print("\r Current Time and Date: " + "[" + dateTime.format(formatter) + "]");
    }

    void updateDateTime(){
        dateTime = LocalDateTime.now();
    }
}

class Main{
    public  static void main(String[] args) {
        Clock clock = new Clock();
    
        Thread updateThread = new Thread(() -> {
            while (true){
                try {
                    clock.updateDateTime();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        Thread displayThread = new Thread(() -> {
            while (true){
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