package com.chen.web.common.thread.endpoint;

import com.chen.web.common.thread.queue.ResizeableCapacityLinkedBlockingQueue;
import com.google.common.collect.ImmutableMap;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author cjw
 */
@Component
@Endpoint(id = "threadPool")
public class ThreadPoolEndPoint {
    private ThreadPoolExecutor executor;

    @ReadOperation
    public Map<String, Number> getThreadPoolStatus() {
        int corePoolSize = executor.getCorePoolSize();
        int maximumPoolSize = executor.getMaximumPoolSize();
        int activeCount = executor.getActiveCount();
        long taskCount = executor.getTaskCount();
        long capacitySize = executor.getQueue().size() + executor.getQueue().remainingCapacity();

        return ImmutableMap.of(
                "corePoolSize", corePoolSize,
                "maximumPoolSize", maximumPoolSize,
                "activeCount", activeCount,
                "taskCount", taskCount,
                "capacitySize", capacitySize);

    }

    public void resize(int corePoolSize, int maximumPoolSize, int capacitySize) {
        executor.setCorePoolSize(corePoolSize);
        executor.setMaximumPoolSize(maximumPoolSize);
        Queue queue = executor.getQueue();
        if (queue instanceof ResizeableCapacityLinkedBlockingQueue) {
            ResizeableCapacityLinkedBlockingQueue resizeableCapacityLinkedBlockingQueue = (ResizeableCapacityLinkedBlockingQueue) queue;
            resizeableCapacityLinkedBlockingQueue.setCapacity(capacitySize);
        }
    }
}
