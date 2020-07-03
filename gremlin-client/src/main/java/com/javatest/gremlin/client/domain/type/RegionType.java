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
public enum RegionType {
    DCN(1), DCI(2), GLOBAL(3), CNLAB(4);

    private int type;

}
