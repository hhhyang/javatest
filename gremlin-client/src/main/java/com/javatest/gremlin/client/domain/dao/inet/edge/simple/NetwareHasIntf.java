package com.javatest.gremlin.client.domain.dao.inet.edge.simple;


import com.baidu.spring.data.gremlin.annotation.Edge;
import com.baidu.spring.data.gremlin.repository.schema.Multiplicity;
import com.javatest.gremlin.client.domain.dao.SimpleTopoEdge;
import com.javatest.gremlin.client.domain.dao.inet.vertex.Interface;
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
@Edge(label = "HAS_INF", multiplicity = Multiplicity.SIMPLE)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NetwareHasIntf extends SimpleTopoEdge<Netware, Interface> {

    public NetwareHasIntf(final String gid, final Netware netware, final Interface intf) {
        super(gid, netware, intf);
    }

}
