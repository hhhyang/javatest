package com.javatest.gremlin.client.domain.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InetLink<HeadT extends TopoVertex, TailT extends TopoVertex> extends Link<HeadT, TailT> {

    // 本端设备ID
    private String locnwid;
    // 本端主机名
    private String lochostname;

    // 远端设备ID
    private String remnwid;

    // 远端主机名
    private String remhostname;

}
