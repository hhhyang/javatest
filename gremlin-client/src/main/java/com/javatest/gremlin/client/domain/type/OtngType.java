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
public enum OtngType {

    WAREHOUSE(1, "WAREHOUSE"),
    DISTRICT(2, "DISTRICT");

    private int id;

    private String type;


}
