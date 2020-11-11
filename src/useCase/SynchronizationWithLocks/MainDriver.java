package useCase.SynchronizationWithLocks;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainDriver {

    public static void main(String[] args) {
        AtomicBoolean processing= new AtomicBoolean(true);
        AtomicBoolean syncing= new AtomicBoolean(true);
        Store store= new Store(new Store.Callback() {
            @Override
            public void syncedThePriceFromServer() {
                System.out.println("main: Okay we have synced the latest prices from server");
                syncing.set(false);
            }

            @Override
            public void generatedTheInvoice(int price) {
                System.out.println("main: got the invoice generated price "+price);
                processing.set(false);
            }
        });
        store.syncThePriceFromServer();
        store.generateInvoice();

        System.out.println("Doing some other work like watching netflix");
        //keeping main thread live
        while (syncing.get() || processing.get()){
            // running
        }
        System.out.println("program terminated !");
    }
}
