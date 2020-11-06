package concurrency.ThreadsInJava;

public class MainDriver {

    public static void main(String[] args) {
        ThreadA threadA= new ThreadA("Thread A");
        ThreadB threadB= new ThreadB("Thread B");

        // dont do threadA.run() it would give results like thread A is completed and then thread B

        // thread B is sleeping for 2 secs and A for 1 sec
        threadA.start();
        threadB.run();
    }
}
