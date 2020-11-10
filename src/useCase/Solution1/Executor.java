package useCase.Solution1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class Executor extends Thread {

    private ConcurrentLinkedQueue<Worker> workers;
    private Builder.Callback callback;
    private CountDownLatch countDownLatch;

    private Executor(List<Runnable> tasks, Builder.Callback callback){
        super();
        this.callback=callback;
        workers=new ConcurrentLinkedQueue<>();
        countDownLatch= new CountDownLatch(tasks.size());

        for(Runnable task:tasks){
            workers.add(new Worker(task,countDownLatch));
        }
    }

    public void execute(){
        start();
    }

    @Override
    public void run() {
        while (true){
            Worker worker= workers.poll();
            if(worker==null)
                break;
            worker.start();
        }
        try{
            countDownLatch.await();
            // this above await would block the below execution until latch count is not zero
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if(callback!=null)
            callback.onComplete();
    }

    public static class Builder{
        private List<Runnable> tasks= new ArrayList<>();
        private Callback callback;

        public Builder add(Runnable task){
            tasks.add(task);
            return this;
        }
        public Builder addCallable(Callback callback){
            this.callback=callback;
            return this;
        }

        public Executor build(){
            return new Executor(tasks,callback);
        }

        public interface Callback{
            void onComplete();
        }
    }
}
