package com.yablokovs.multithreading;

import java.util.concurrent.atomic.AtomicBoolean;

public class StopThread implements Runnable {

    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval;

    public StopThread(int sleepInterval) {
        interval = sleepInterval;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    boolean isRunning() {
        return running.get();
    }

    boolean isStopped() {
        return !running.get();
    }

    public void run() {
        running.set(true);
        while (running.get()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            running.set(false);

        }
    }
}
