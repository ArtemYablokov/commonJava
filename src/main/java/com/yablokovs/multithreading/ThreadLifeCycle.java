package com.yablokovs.multithreading;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/*
 *
 * NEW – a newly created thread that has not yet started the execution
 * RUNNABLE – either running or ready for execution but it's waiting for resource allocation
 * BLOCKED – waiting to acquire a monitor lock to enter or re-enter a synchronized block/method
 * WAITING – waiting for some other thread to perform a particular action without any time limit
 * TIMED_WAITING – waiting for some other thread to perform a specific action for a specified period
 * TERMINATED – has completed its execution
 *
 * */


@Slf4j
public class ThreadLifeCycle {

//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CommonExample.class);

    public static Thread thread;

    @SneakyThrows
    public static void main(String[] args) {
        /*Thread*/
        thread = Thread.currentThread();

        Thread t = new Thread(getRunnable());
        // log.info("NEW THREAD: {}", t.getState());
        t.start();
        Thread.sleep(500);

        log.info("CHILD: {}", t.getState());

        // t.join();
        // LockSupport.park();

        //thread.join();
        log.info("THREAD: {}", thread.getState());

        try {
            t.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(500);
        log.info("CHILD: {}", t.getState());


        log.info("THREAD: {} ", thread.getState());

        // Thread.sleep(100);
        // log.info("INTERRUPTED THREAD: {}", t.getState());

        // t.join();
    }


    public static Runnable getRunnable() {
        return new Runnable() {

            @Override
            public void run() {
                /*for (int i = 0; i < 3; i++)*/ while (true){
                    log.info("I'm running");
//                    log.info("Current THREAD: {} ", Thread.currentThread().getState());
                    /*try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    log.info("THREAD: {} ", thread.getState());
                }
            }
        };

    }
}
