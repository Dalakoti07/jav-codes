package useCase.Solution2;

import useCase.Solution2.model.Activity;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class App {

    public static void main(String[] args) {
        System.out.println("Program Started");
        AtomicBoolean processing = new AtomicBoolean(true);

        RemoteService service = new RemoteService();
        service.getUserCurrentActivities(activities -> {
            for (Activity activity : activities) {
                System.out.println(activity);
            }
            processing.set(false);
        });
        System.out.println("doing some other task...");
        for(int i=0;i<100;i++){
            if(i%5==0)
                System.out.println("Counting i: "+i+" is multiple of 5");
        }

        while (processing.get()){
            // keep running
        }
        service.stop();
        System.out.println("Program Terminated");
    }
}
