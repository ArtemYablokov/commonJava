package com.yablokovs.multithreading;


/**
 *
 * Ромина задача на ограничение запросов для одного пользователя за секунду
 *
 * */
//Each customer can make X requests per Y seconds
//boolean rateLimit(int customerId)
import java.util.*;
import java.util.concurrent.atomic.*;

public class RateLimiterPerCustomerPerSecond {

    public static void main(String[] args) {
        RateLimiterPerCustomerPerSecond limiter = new RateLimiterPerCustomerPerSecond();

        System.out.println(limiter.rateLimit(1));
        System.out.println(limiter.rateLimit(1));
        System.out.println(limiter.rateLimit(1));
        try {
            Thread.sleep(1010);
        }  catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(limiter.rateLimit(1));
    }

    private static class Wrapper {
        AtomicInteger number;
        Long seconds;

        public Wrapper() {
            number = new AtomicInteger(1);
            seconds = System.currentTimeMillis() / 1000L;
        }

        public boolean allow(int limit) {
            Long second = System.currentTimeMillis() / 1000L;

            boolean nextSecond = !second.equals(seconds);
            if (nextSecond) {
                number.set(0);
                seconds = second;
            }

            return number.get() < limit;
        }

        public void inc() {
            number.incrementAndGet();
        }
    }

    private final Map<Integer, Wrapper> requests = new HashMap<>();
    private final int limit = 2;

    public RateLimiterPerCustomerPerSecond() {

    }

    public boolean rateLimit(int customerId) {
        Wrapper wrapper = requests.get(customerId);
        if (wrapper == null) {
            requests.put(customerId, new Wrapper());
            return true;
        }

        if (wrapper.allow(limit)) {
            wrapper.inc();
            return true;
        }

        return false;
    }
}