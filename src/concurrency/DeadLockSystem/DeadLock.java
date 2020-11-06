package concurrency.DeadLockSystem;

public class DeadLock {

    Object mLock1 = new Object();
    Object mLock2 = new Object();
    public void methodA() {
        System.out.println("methodA wait for mLock1 " + Thread.currentThread().getName());
        synchronized (mLock1) {
            System.out.println("methodA mLock1 acquired" + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
                methodB();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public void methodB() throws InterruptedException {
        System.out.println("methodB wait for mLock2 " + Thread.currentThread().getName());
        synchronized (mLock2) {
            System.out.println("methodB mLock2 acquired " + Thread.currentThread().getName());
            Thread.sleep(100);
            methodC();
        }
    }
    public void methodC() throws InterruptedException {
        System.out.println("methodC waiting for mLock1 "+ Thread.currentThread().getName());
        synchronized (mLock1) {
            System.out.println("method3 mLock1 acquired " + Thread.currentThread().getName());
        }
    }
}
