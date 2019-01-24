package com.king.re0.base.aotu;


import org.springframework.stereotype.Component;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ExecutorManager {

    private final ExecutorService exec = Executors.newFixedThreadPool(100);

    //    private final Scheduler scheduler = Schedulers.fromExecutor(exec);
    public Scheduler getScheduler() {
        return Schedulers.fromExecutor(exec);
    }
}
