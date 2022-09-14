package com.yablokovs.multithreading.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;


// TODO: 13.09.2022 Try.of for EXCEPTIONS in LAMBDA
// https://github.com/skopylov58/java-functional-addons
// https://intellipaat.com/community/6786/how-to-add-local-jar-files-to-a-maven-project
public class FutureExample {

    public static void main(String[] args) {


        int sum = IntStream.range(0, 1)
                .filter(x ->
                {
                    sleepWithHandledException();
                    return x > 0;
                }).sum();

        Future<String> completableFuture = getCompletableFuture();
    }

    public Future<String> getFuture() {
        return getFixedThreadPoolExecutorService(2).submit(() -> {
            System.out.println("Runnable executed");
        }, "result after Runnable");
    }

    private static Future<String> getCompletableFuture() {
        return CompletableFuture.supplyAsync(() -> {
            sleepWithHandledException();
            return "run in parallel";
        });
    }

    private static void sleepWithHandledException() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private ExecutorService getFixedThreadPoolExecutorService(int numberOfThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        return executorService;
    }
}
