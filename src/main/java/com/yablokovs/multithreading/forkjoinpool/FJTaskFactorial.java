package com.yablokovs.multithreading.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FJTaskFactorial extends RecursiveTask<Integer> {

    @SneakyThrows
    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        FJTaskFactorial fjTaskFactorial = new FJTaskFactorial(4);

        forkJoinPool.execute(fjTaskFactorial);

        while (!fjTaskFactorial.isDone()) {
        }

        log.info("Factorial of {} = " + fjTaskFactorial.get(), 4);
    }


    private Integer n;

    public FJTaskFactorial(Integer n) {
        this.n = n;
    }

    public Integer getN() {
        return n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        FJTaskFactorial calculator = new FJTaskFactorial(n - 1);
        calculator.fork();
        return n * calculator.join();
    }

}
