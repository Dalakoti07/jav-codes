package concurrency.syncronization;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Syncronization {
    private static int t = 0;
    private static Object mutex = new Object();
    public static void main(String[] args) {
        // we are creating 400 threads and each one can increment the counter, but we are using synchronized keyword to make sure that we achieve
        // consistency
        /*
        * In Java, there is a built-in language-level locking mechanism: the synchronized block, which can use any Java object
            as an intrinsic lock (i.e. every Java object may have a monitor associated with it).
            Intrinsic locks provide atomicity to groups of statements. To understand what that means for us, let's have a look at
            an example where synchronized is useful:
        * In this case, if it weren't for the synchronized block, there would have been multiple concurrency issues involved.
            The first one would be with the post increment operator (it isn't atomic in itself), and the second would be that we
            would be observing the value of t after an arbitrary amount of other threads has had the chance to modify it.
            However, since we acquired an intrinsic lock, there will be no race conditions here and the output will contain
            numbers from 1 to 100 in their normal order
        * */
        ExecutorService executorService = Executors.newFixedThreadPool(400); // The high thread count is for demonstration purposes.
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                synchronized (mutex) {
                    t++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(MessageFormat.format("with synchronized t: {0}", t));
                }
            });
        }

        System.out.println("When we don't use syncronization ");

        // if we dont use then we can see that multi threads can access the critical section
        ExecutorService executorService2 = Executors.newFixedThreadPool(400); // The high thread count is for demonstration purposes.
        for (int i = 100; i < 200; i++) {
            executorService2.execute(() -> {
                t++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Unable to sleep");
                    e.printStackTrace();
                }
                System.out.println(MessageFormat.format("without synchronized t: {0}", t));
            });
        }
        // don't forget to shutdown the executor

        executorService.shutdown();
        executorService2.shutdown();

        // if we are using synchronized keyword then only one thread at a time
    }
}
