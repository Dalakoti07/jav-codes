package concurrency.SimpleMultiThreading;

public class PrintAndCount implements Runnable {

    private final String name;

    PrintAndCount(String name) {
        this.name = name;
    }

    /**
     * This is what a CountAndPrint will do
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.name + ": " + i);
        }
    }

    public static void main(String[] args) {
        // Launching 4 parallel threads
        for (int i = 1; i <= 4; i++) {
        // `start` method will call the `run` method
        // of CountAndPrint in another thread
            new Thread(new PrintAndCount("Instance " + i)).start();
        }
        // Doing some others tasks in the main Thread
        for (int i = 0; i < 10; i++) {
            System.out.println("Main: " + i);
        }
    }
}
