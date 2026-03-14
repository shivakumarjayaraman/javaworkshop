package org.spjain.bds.threads;

public class SimpleThread {
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Hello from Thread 1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable r = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hello from Runnable");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
        System.out.println("Started");
        t1.join();
        t2.join();
        System.out.println("All done");
    }
}