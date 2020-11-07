package ExecutorThreadPools.Executor;

import java.util.Random;
import java.util.concurrent.*;

public class MainDriver {
    //The Executor interface in Java provides a way of decoupling task submission from the mechanics of how each task
    //will be run, including details of thread use, scheduling, etc. An Executor is normally used instead of explicitly
    //creating threads. With Executors, developers won't have to significantly rewrite their code to be able to easily tune
    //their program's task-execution policy.

    public static void main(String[] args) {
            ThreadPoolExecutor pool = new ThreadPoolExecutor(1,
                // keep at least one thread ready,
                // even if no Runnables are executed
                5,
                // at most five Runnables/Threads
                // executed in parallel
                10, TimeUnit.SECONDS,
                // idle Threads terminated after one
                // minute, when min Pool size exceeded
                new ArrayBlockingQueue<Runnable>(10)); // outstanding Runnables are kept here
                pool.execute(new Runnable() {
                    @Override public void run() {
                        //code to run
                    }
                });

            // service
            //If your computation produces some return value which later is required, a simple Runnable task isn't sufficient. For
            //such cases you can use ExecutorService.submit(Callable<T>) which returns a value after execution completes.
            //The Service will return a Future which you can use to retrieve the result of the task execution.
            ExecutorService executorService = pool;
            Future<Integer> future = executorService.submit(new Callable<Integer>() {
                @Override public Integer call() {
                //do some computation
                    return new Random().nextInt();
                }
            });

        try {
            future.get(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
