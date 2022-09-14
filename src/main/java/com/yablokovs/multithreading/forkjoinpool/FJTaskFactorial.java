package com.yablokovs.multithreading.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import lombok.SneakyThrows;

public class FJTaskFactorial extends RecursiveTask<Integer> {


    @SneakyThrows
    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        FJTaskFactorial calculator = new FJTaskFactorial(10);

        forkJoinPool.execute(calculator);

        Integer compute = calculator.get();

        System.out.println(compute);

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

        FJTaskFactorial calculator
                = new FJTaskFactorial(n - 1);

        calculator.fork();

        return n + calculator.join();
    }

}
