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
public enum NetwareGroupType {
    // http://wiki.baidu.com/pages/viewpage.action?pageId=400187483
    IDC(1), DC(2), SC(3), RR(4), AREA(5), CLUSTER(6), BGW(7), BIGNAT(8),
    CDC(9), EB(10), EC(11), MON(12), BC(13), ADC(14), CEC(15),
    CEB(16), CADC(17), SPINE(18), POD(19), ASC(20), ACLUSTER(21), ERR(22),
    ILO(23);

    private int type;
}
