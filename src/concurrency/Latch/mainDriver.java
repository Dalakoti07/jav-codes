package concurrency.Latch;

import java.util.concurrent.CountDownLatch;

public class mainDriver {
    /*
        CountDownLatch
            A synchronization aid that allows one or more threads to wait until a set of operations being performed
            in other threads completes.
                1. A CountDownLatch is initialized with a given count.
                2. The await methods block until the current count reaches zero due to invocations of the countDown() method,
                after which all waiting threads are released and any subsequent invocations of await return immediately.
                3. This is a one-shot phenomenon—the count cannot be reset. If you need a version that resets the count,
                consider using a CyclicBarrier .

        Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted.

        1. CountDownLatch is initialized with a counter of 5 in Main thread
        2. Main thread is waiting by using await() method.
        3. Five instances of DoSomethingInAThread have been created. Each instance decremented the counter with
        countDown() method.
        4. Once the counter becomes zero, Main thread will resume
     */
    public static void main(String[] args) {
        try {
            int numberOfThreads = 3;
            /*if (args.length < 1) {
                System.out.println("Usage: java CountDownLatchDemo numberOfThreads");
                return;
            }
            try {
                numberOfThreads = Integer.parseInt(args[0]);
            } catch(NumberFormatException ne) {
            }*/
            CountDownLatch latch = new CountDownLatch(numberOfThreads);
            for (int n = 0; n < numberOfThreads; n++) {
                Thread t = new Thread(new DoSomethingInAThread(latch));
                t.start();
            }
            latch.await();
            System.out.println("In Main thread after completion of " + numberOfThreads + " threads");
        } catch(Exception err) {
            err.printStackTrace();
        }
    }
}
