package com.chen.web.common.thread;

import com.chen.common.utils.AsyncTask;
import com.chen.web.common.thread.mode.request.ThreadResizeRequest;
import com.chen.web.common.thread.queue.ResizeableCapacityLinkedBlockingQueue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

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

    private ThreadPoolExecutor threadPool;

    public void resize(ThreadResizeRequest request) {
        if (request.getCorePoolSize() != null) {
            resizeCorePoolSize(request.getCorePoolSize());
        }
        if (request.getMaximumPoolSize() != null) {
            resizeMaximumPoolSize(request.getMaximumPoolSize());
        }
        if (request.getQueueCapacity() != null) {
            resizeCapacitySize(request.getQueueCapacity());
        }
    }


    public void resizeCorePoolSize(int corePoolSize) {
        threadPool.setCorePoolSize(corePoolSize);
    }

    public void resizeMaximumPoolSize(int maximumPoolSize) {
        threadPool.setMaximumPoolSize(maximumPoolSize);
    }

    public void resizeCapacitySize(int capacitySize) {
        Queue queue = threadPool.getQueue();
        if (queue instanceof ResizeableCapacityLinkedBlockingQueue) {
            ResizeableCapacityLinkedBlockingQueue resizeableCapacityLinkedBlockingQueue = (ResizeableCapacityLinkedBlockingQueue) queue;
            resizeableCapacityLinkedBlockingQueue.setCapacity(capacitySize);
        }
    }
}
