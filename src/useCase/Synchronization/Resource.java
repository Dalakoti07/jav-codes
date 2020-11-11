package useCase.Synchronization;

import java.util.concurrent.atomic.AtomicBoolean;

public class Resource {
    private AtomicBoolean disallowed= new AtomicBoolean(false);

    public synchronized void setDisallowed(){
        disallowed.set(true);
        System.out.println("Now resource is disallowed now ..");
    }

    public synchronized void process(){
        if(!disallowed.get()){
            try {
                Thread.sleep(2000);
                System.out.println("I processed because processing was allowed "+disallowed.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Could not be processed, it was not allowed "+disallowed.get());
        }
    }
}
