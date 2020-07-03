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
public enum OtnOperStatus {

    ACTIVE(1), INACTIVE(2), DISABLE(3), UNKNOWN(4);

    private int operStatus;


}
