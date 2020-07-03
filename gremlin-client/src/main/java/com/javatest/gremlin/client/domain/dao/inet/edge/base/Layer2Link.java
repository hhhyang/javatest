package com.javatest.gremlin.client.domain.dao.inet.edge.base;


import com.baidu.spring.data.gremlin.annotation.Edge;
import com.baidu.spring.data.gremlin.repository.schema.Multiplicity;
import com.javatest.gremlin.client.domain.dao.InetLink;
import com.javatest.gremlin.client.domain.dao.inet.vertex.Netware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Edge(label = "L2_LINK", multiplicity = Multiplicity.MULTI)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Layer2Link extends InetLink<Netware, Netware> {


    // 本端ifid
    private String locifid;
    // 本端接口名
    private String locifname;

    // 远端ifid
    private String remifid;
    // 远端接口名
    private String remifname;
    // 链路带宽
    private Long bandwidth;


}
