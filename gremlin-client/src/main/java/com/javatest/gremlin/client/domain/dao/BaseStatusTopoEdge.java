package com.javatest.gremlin.client.domain.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Data
@NoArgsConstructor
public abstract class BaseStatusTopoEdge<ElementID extends Serializable,
        HeadT extends TopoVertex, TailT extends TopoVertex>
        extends BaseTopoEdge<ElementID, HeadT, TailT>
        implements BaseStatusTopoElement<ElementID, String>, TopoEdge<HeadT, TailT> {

    @Override
    public abstract ElementID getElementId();

    @Override
    public abstract String getOperationStatus();

}
