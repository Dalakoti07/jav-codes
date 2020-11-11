package useCase.Synchronization;

public class MainDriver {

    public static void main(String[] args) {
        Resource resource= new Resource();

        Thread thread1= new Thread(()->{
           resource.process();
        });
        Thread thread2= new Thread(()->{
            resource.setDisallowed();
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
