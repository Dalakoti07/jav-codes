package concurrency.ThreadsInJava;

public class ThreadA extends Thread {

    public ThreadA(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA running! ");
        }
    }
}
