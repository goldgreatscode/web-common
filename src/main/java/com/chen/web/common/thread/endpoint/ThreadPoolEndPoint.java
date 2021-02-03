package com.chen.web.common.thread.endpoint;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author cjw
 */
@Component
@Endpoint(id = "threadPool")
@AllArgsConstructor
public class ThreadPoolEndPoint {
    private ThreadPoolExecutor threadPool;

    @ReadOperation
    public Map<String, Number> getThreadPoolStatus() {
        int corePoolSize = threadPool.getCorePoolSize();
        int maximumPoolSize = threadPool.getMaximumPoolSize();
        int activeCount = threadPool.getActiveCount();
        long taskCount = threadPool.getTaskCount();
        long capacitySize = threadPool.getQueue().size() + threadPool.getQueue().remainingCapacity();

        return ImmutableMap.of(
                "corePoolSize", corePoolSize,
                "maximumPoolSize", maximumPoolSize,
                "activeCount", activeCount,
                "taskCount", taskCount,
                "capacitySize", capacitySize);

    }

}
