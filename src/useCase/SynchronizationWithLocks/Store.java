package useCase.SynchronizationWithLocks;

import useCase.Solution1.Executor;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Store {
    private List<Item> allItems= Arrays.asList(new Item("Chair",10)
            ,new Item("Table",20),new Item("CPU",50));
    // creating a thread pool executor
    private ExecutorService executorService= Executors.newFixedThreadPool(2);
    private Callback callback;
    private ReadWriteLock readWriteLock= new ReentrantReadWriteLock();

    public Store(Callback callback){
        this.callback=callback;
    }

    public void syncThePriceFromServer(){
        Lock writeLock= readWriteLock.writeLock();
        executorService.execute(()->{
            writeLock.lock();
            //System.out.println("Syncing the data from server...");
            try {
                Thread.sleep(3000);
                allItems.get(0).setPrice(20);
                allItems.get(0).setPrice(30);
                allItems.get(0).setPrice(60);
                System.out.println("Got all the synced data from server and hiked total price is "+getTotalPrice());
                callback.syncedThePriceFromServer();
                writeLock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void generateInvoice(){
        Lock readLock= readWriteLock.readLock();
        executorService.execute(()->{
            readLock.lock();
            int totalPrice=getTotalPrice();
            //System.out.println("Your invoice has been generated for price of amount "+totalPrice);
            callback.generatedTheInvoice(totalPrice);
            readLock.unlock();
        });
    }

    private int getTotalPrice(){
        int totalPrice=0;
        for(Item item: allItems){
            totalPrice+=item.getPrice();
        }
        return totalPrice;
    }

    public interface Callback{
        void syncedThePriceFromServer();

        void generatedTheInvoice(int price);
    }
}
