package concurrency.CountThreads;

public class MyThread implements Runnable{
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
