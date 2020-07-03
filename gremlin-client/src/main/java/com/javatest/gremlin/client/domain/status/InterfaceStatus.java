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
public enum InterfaceStatus {
    UP(1),
    DOWN(2),
    TESTING(3);

    private int status;


}
