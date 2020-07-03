package com.javatest.gremlin.client.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Getter
@AllArgsConstructor
public enum SrLabelType {
    ADJACENCY(0), PREFIX(1);

    private int type;
}
