package com.javatest.gremlin.client.domain.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Getter
@AllArgsConstructor
public enum NetwareOperStatus {
    UP(1), DOWN(2);

    private int operStatus;

}
