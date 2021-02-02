package com.chen.web.common.thread;

import com.chen.common.utils.AsyncTask;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * @author cjw
 */
@Component
@AllArgsConstructor
public class ThreadPoolService {
    @PostConstruct
    public void init() {
        System.out.println("threadPool inited");
        AsyncTask.setExecutor(threadPool);
    }

    private Executor threadPool;

}
