package concurrency.CallablesAndFuture;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainDriver {
    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        System.out.println("Time At Task Submission : " + new Date());
        Future<String> result = es.submit(new ComplexCalculator());
        // the call to Future.get() blocks until the result is available.So we are in for about a 10 sec wait now
        try {
            //getting the results from callable in next line
            System.out.println("Result of Complex Calculation is : " + result.get());
            //doing another task below
            System.out.println("doing some other task");
            System.out.println("Time At the Point of Printing the Result : " + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
