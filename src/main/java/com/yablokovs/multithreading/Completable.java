package com.yablokovs.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Completable {
//        completableFuture.thenRunAsync().thenAccept()


    @SneakyThrows
    public static void main(String[] args) {
//        Future<String> stringFuture = calculateAsync();

//         Thread.sleep(5000);
//        System.out.println(stringFuture.isDone());
//
//        log.info("THREAD: {}", Thread.currentThread().getState());

        runAsync();

        /*CompletableFuture<Void> string = CompletableFuture.runAsync(() -> {
            System.out.println("string");
        });*/

        Thread.sleep(1);
        System.out.println("break");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("string");
            return "string";
        });

        completableFuture.thenApply(s -> s);
        completableFuture.thenAccept(s -> {});
        completableFuture.thenRun(() -> {})
                .thenAccept(s -> {

                    System.out.println(s);
                });

        completableFuture.thenCompose(s -> completableFuture);

        Thread.sleep(5000);

        System.out.println(completableFuture.get());

    }

    private static void runAsync() {
        CompletableFuture<Void> string = CompletableFuture.runAsync(() -> {
            System.out.println("string");
        });
    }


    public static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();


        Callable<Object> hello = () -> {
            log.info("completableFuture");
            Thread.sleep(5000);
            completableFuture.complete("Hello");
            return null;
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(hello);

        return completableFuture;
    }

}
