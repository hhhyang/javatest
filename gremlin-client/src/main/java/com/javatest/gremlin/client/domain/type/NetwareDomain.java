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
public enum NetwareDomain {

    INT(1, "INT", "INT"),
    EXT(2, "EXT", "EXT"),
    ADMIN(3, "ADMIN", "ADMIN"),
    CIN(4, "CIN", "CIN");

    private int id;

    private String name;

    private String description;


}
