package cuong.sample.thread.interrupt;

public class InterruptibleThread extends Thread{

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("task");
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted...\n" + e);
        }
        System.out.println("thread is running...");
    }

    public static void main(String args[]) {
        InterruptibleThread t1 = new InterruptibleThread();
        t1.start();
        try {
            t1.interrupt();
        } catch (Exception e) {
            System.out.println("Exception handled \n" + e);
        }
    }

}
