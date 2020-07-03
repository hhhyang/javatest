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
public enum ProtocolType {
    OSPF(0), ISIS(1), IBGP(2), EBGP(3);

    private int type;

}
