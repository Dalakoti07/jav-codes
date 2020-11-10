package useCase.Solution1;

import java.util.concurrent.atomic.AtomicBoolean;

public class mainDriver {

    public static void main(String[] args) {
        AtomicBoolean processing= new AtomicBoolean(true);
        // in android main thread is kept alive by OS, but here we have to do this on our own and we do this is following way

        new Executor.Builder().add(()->{
            // this is a runnable which would be executed in seperate thread by worker
            System.out.println("Doing task 1 ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 1 completed ");
        }).add(() -> {
            System.out.println("Doing task 2 ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 2 completed ");
        }).add(()->{
            System.out.println("Doing task 3 ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 3 completed ");
        }).addCallable(() -> {
            System.out.println("Every task - all 3 tasks are done, callback called " );
            processing.getAndSet(false);
        }).build()
                .execute();

        while (processing.get()) {
            // program runs continuously
        }
        System.out.println("Program Terminates");

        // as expected we get
        // Doing task 1
        //Doing task 2
        //Doing task 3
        //Task 2 completed
        //Task 1 completed
        //Task 3 completed
        //Every task - all 3 tasks are done, callback called
    }
}
