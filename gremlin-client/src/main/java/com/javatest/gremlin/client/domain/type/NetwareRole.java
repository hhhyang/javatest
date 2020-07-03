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
public enum NetwareRole {

    // 内网设备
    DC(1, "DC", "DC"),
    IC(2, "IC", "IC"),
    IB(3, "IB", "IB"),
    SC(4, "SC", "SC"),
    RR(5, "RR", "RR"),
    ADC(6, "ADC", "ADC"),

    // CLOS设备
    SPINE(7, "SPINE", "SPINE"),
    LEAF(8, "LEAF", "LEAF"),

    // 外网设备
    EC(9, "EC", "EC"),
    EB(10, "EB", "EB"),
    ERR(11, "ERR", "ERR"),
    BC(12, "BC", "BC"),

    // 公有云设备
    CDC(13, "CDC", "CDC"),
    CADC(14, "CADC", "CADC"),
    CIC(15, "CIC", "CIC"),
    CIB(16, "CIB", "CIB"),
    CEC(17, "CEC", "CEC"),
    CEB(18, "CEB", "CEB"),

    // 公有云CLOS设备
    CSPINE(19, "CSPINE", "cloud Spine"),
    CLEAF(20, "CLEAF", "cloud Leaf"),

    // 管理网设备
    ASC(21, "ASC", "ASC"),
    AC(22, "AC", "AC"),
    AB(23, "AB", "AB"),
    AA(24, "AA", "AA"),

    // ILO设备
    ILO(25, "ILO", "ILO"),

    // 全流量设备
    MON(26, "MON", "MON");

    private int id;

    private String name;

    private String description;



}
