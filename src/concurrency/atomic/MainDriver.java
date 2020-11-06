package concurrency.atomic;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainDriver {
    static int t=0;
    private static AtomicInteger atomicInteger = new AtomicInteger(299);

    public static void main(String[] args) {
        // a code which is not following concurrency and using simple int
        ExecutorService executorService = Executors.newFixedThreadPool(400); // The high thread count is for demonstration purposes.
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                t++;
                System.out.println(MessageFormat.format("simple int value t: {0}", t));
            });
        }
        executorService.shutdown();

        // using atomic integer
        //The incrementAndGet method of AtomicInteger atomically increments and returns the new value, thus eliminating
        //the previous race condition.
        // Important
        // Please note that in this example the lines will still be out of order because we make no
        //effort to sequence the println calls and that this falls outside the scope of this example, since it would require
        //synchronization and the goal of this example is to show how to use AtomicInteger to eliminate race conditions
        //concerning state.
        System.out.println("Using atomic integer");
        ExecutorService executorService1 = Executors.newFixedThreadPool(400); // The high thread count is for demonstration purposes.
        for (int i = 0; i < 100; i++) {
            executorService1.execute(() -> {
                int currentT = atomicInteger.incrementAndGet();
                System.out.println(MessageFormat.format("atomic t: {0}", currentT));
            });
        }
        executorService1.shutdown();
    }
}
