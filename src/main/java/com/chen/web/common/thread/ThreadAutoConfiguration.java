package com.chen.web.common.thread;

import com.chen.web.common.thread.queue.ResizeableCapacityLinkedBlockingQueue;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cjw
 */
@Configuration
public class ThreadAutoConfiguration {


    /**
     * 异步线程
     *
     * @return
     */
    @Bean(value = "threadPool")
    public ThreadPoolExecutor buildThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("async-queue-thread-%d").build();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new ResizeableCapacityLinkedBlockingQueue<>(5), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }

}
