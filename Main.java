package com.any.assessment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

// Custom annotation to mark methods for execution time logging
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {
    TimeUnit value() default TimeUnit.MILLISECONDS;
}

class MyService {
    @LogExecutionTime
    public void performTask() {
        // Simulate some work
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyService service = new MyService();
        service.performTask(); // This will log the execution time
    }
}

