package com.github.knextsunj.micronautlearn.util;

import io.micronaut.context.ApplicationContext;

import java.util.concurrent.locks.ReentrantLock;

public class ApplicationContextBuilderHelper {

    private static volatile ApplicationContext ctx;
    private static final ReentrantLock lock = new ReentrantLock();

    public static void setApplicationContext(ApplicationContext context) {
        if (ctx != null) throw new IllegalStateException("Already initialized!");

        if (lock.tryLock()) {
            try {
                if (ctx == null) {
                    ctx = context;
                    System.out.println("Context initialized using ReentrantLock.");
                }
            } finally {
                lock.unlock();
            }
        } else {
            throw new IllegalStateException("Context is being initialized by another thread.");
        }
    }

    public static ApplicationContext getApplicationContext() {
        if (ctx == null) {
            throw new IllegalStateException("Context not yet initialized.");
        }
        return ctx;
    }
}
