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
public enum NetwareAdminStatus {
    online(1), offline(2), checking(3), standby(4), deleted(5), testing(6), fault_or_change(7), virtual(8),
    unknown(9);

    private int adminStatus;


}
