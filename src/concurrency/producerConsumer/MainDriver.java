package concurrency.producerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainDriver {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<ProducedData> queue = new ArrayBlockingQueue<ProducedData>(1000);
        // choice of queue determines the actual behavior: see various BlockingQueue implementations
        Thread producer = new Thread(new Producer(queue));

        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
        Thread.sleep(1000);
        producer.interrupt();
        Thread.sleep(10);
        // sometime consumer consume all the objects and sometime it does not
        consumer.interrupt();
    }
}
