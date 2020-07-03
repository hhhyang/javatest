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
public enum InterfaceType {
    PHYSICAL(100),
    VLAN(200),
    AGGREGATOR(300),
    TUNNEL(400),
    LOOPBACK(500),
    MANAGE(600),
    MPLS(700),
    OTHER(800);

    private int type;

}
