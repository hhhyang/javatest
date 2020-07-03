package com.javatest.gremlin.client.domain.dao.inet.vertex;

import com.baidu.spring.data.gremlin.annotation.Index;
import com.baidu.spring.data.gremlin.annotation.Vertex;
import com.javatest.gremlin.client.domain.dao.BaseStatusTopoVertex;
import com.javatest.gremlin.client.domain.status.NetwareAdminStatus;
import com.javatest.gremlin.client.domain.status.NetwareOperStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 网络设备Entity
 *
 * @author yangshengbing@baidu.com
 */
@Vertex(label = "Netware")
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Netware extends BaseStatusTopoVertex<String> {

    // 设备唯一标识
    @Index(unique = true)
    private String nwid;

    private String id;

    // 设备管理ip (增加index以对前端模糊检索提速)
    @Index(unique = true)
    private String mgmtIp;

    // 启动后运行时间
    private String uptime;
    // 设备名 (增加index以对前端模糊检索提速)
    @Index
    private String hostname;

    // routeid
    private String routeid;
    // 机房
    private String warehouse;
    // 集群
    @Index
    private String cluster;
    @Index
    private String pod;

    // ospf areaid
    private String areaid;
    // isis systemid
    private String systemid;
    // role (SC/DC/B)
    private String role;
    // domain(Int/Ext)
    private String domain;
    // 区域(HB/HN/HD)
    private String district;
    // 厂商
    private String vendor;
    // 型号
    private String model;
    // 设备描述
    private String description;
    // 网段
    private String net;

    private String rackno;
    private String stackType;
    private String softwareVersion;
    private String logicRegion;


    // 设备管理状态
    private NetwareAdminStatus adminStatus;
    // 设备状态
    private NetwareOperStatus operStatus;

    @Override
    @JsonIgnore
    public String getElementId() {
        return mgmtIp;
    }

    @Override
    @JsonIgnore
    public String getOperationStatus() {
        return operStatus.name();
    }
}
