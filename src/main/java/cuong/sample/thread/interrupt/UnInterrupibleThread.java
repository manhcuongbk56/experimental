package cuong.sample.thread.interrupt;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class UnInterrupibleThread extends Thread {

    public static CountDownLatch threadRunningLatch = new CountDownLatch(1);
    public static volatile boolean isDidTryInterruptThread = false;

    public void run() {
        threadRunningLatch.countDown();
        while (!isDidTryInterruptThread){

        }
        System.out.println("thread done running!!!!");
    }

    public static void main(String args[]) throws InterruptedException {
        UnInterrupibleThread t1 = new UnInterrupibleThread();
        t1.start();
        threadRunningLatch.await();
        try {
            t1.interrupt();
        } catch (Exception e) {
            System.out.println("Exception handled \n" + e);
        }
        isDidTryInterruptThread = true;
    }

}
