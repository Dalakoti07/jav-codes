package concurrency.ThreadsInJava;


import static java.lang.Thread.sleep;

public class ThreadB implements Runnable {
    String name;

    public ThreadB(String name){
        this.name=name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadB running! ");
        }
    }
}
