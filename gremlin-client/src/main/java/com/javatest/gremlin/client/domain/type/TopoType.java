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
public enum TopoType {
    NORMAL(1),
    LEGACY(2),
    CLOS(3),
    CLOUD(4),
    CDN(5),
    GLOBAL(6),
    CNLAB(7),
    B1(8),
    B2(9),
    GI(10),
    B99(11),
    B98(12);

    private int type;

}