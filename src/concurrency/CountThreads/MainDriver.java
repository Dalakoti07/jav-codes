package concurrency.CountThreads;

import java.util.Set;

public class MainDriver {
    public static void main(String args[]) throws Exception {
        /*
        Thread.getAllStackTraces().keySet() returns all Thread s including application threads and system threads. If
        you are interested only in status of Threads, started by your application, iterate the Thread set by checking Thread
        Group of a particular thread against your main program thread.
        In absence of above ThreadGroup condition, the program returns status of below System Threads:
            Reference Handler
            Signal Dispatcher
            Attach Listener
            Finalizer
         */
        for (int i = 0; i < 5; i++){
            Thread t = new Thread(new MyThread());
            t.setName("MyThread:" + i);
            t.start();
        }
        int threadCount = 0;
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for (Thread t : threadSet) {
            if (t.getThreadGroup() == Thread.currentThread().getThreadGroup()) {
                System.out.println("Thread :" + t + ":" + "state:" + t.getState());
                ++threadCount;
            }
        }
        System.out.println("Thread count started by Main thread:" + threadCount);
    }
}
