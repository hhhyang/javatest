package com.javatest.gremlin.client.domain.dao.inet.edge.simple;


import com.baidu.spring.data.gremlin.annotation.Edge;
import com.baidu.spring.data.gremlin.repository.schema.Multiplicity;
import com.javatest.gremlin.client.domain.dao.SimpleTopoEdge;
import com.javatest.gremlin.client.domain.dao.inet.vertex.Interface;
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
public class InterfaceHasIntf extends SimpleTopoEdge<Interface, Interface> {

    public InterfaceHasIntf(final String gid, final Interface pIntf, final Interface cIntf) {
        super(gid, pIntf, cIntf);
    }


}
