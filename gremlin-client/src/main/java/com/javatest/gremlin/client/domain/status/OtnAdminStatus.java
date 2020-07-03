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
public enum OtnAdminStatus {

    ENABLE(1), DISABLE(2), MAINT(3), UNKNOWN(4);

    private int adminStatus;

}
