package com.chen.web.common.thread.mode.request;

import lombok.Data;

/**
 * @author cjw
 */
@Data
public class ThreadResizeRequest {
    private Integer corePoolSize;

    private Integer maximumPoolSize;

    private Integer queueCapacity;
}
