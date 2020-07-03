package com.javatest.gremlin.client.domain.dao;

import com.baidu.spring.data.gremlin.annotation.EdgeHead;
import com.baidu.spring.data.gremlin.annotation.EdgeTail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public abstract class BaseTopoEdge<ElementID extends Serializable, HeadT extends TopoVertex, TailT extends TopoVertex>
        extends AbstractBaseTopoElement<ElementID, String> implements TopoEdge<HeadT, TailT> {

    @EdgeHead
    @JsonIgnore
    private transient HeadT head;

    @EdgeTail
    @JsonIgnore
    private transient TailT tail;


    public BaseTopoEdge<ElementID, HeadT, TailT> copy(final BaseTopoEdge<ElementID, HeadT, TailT> base,
                                           final HeadT head,
                                           final TailT tail,
                                           final String source,
                                           final Long timestamp) {

        super.copy(base, source, timestamp);

        this.head = head;
        this.tail = tail;

        return this;
    }

    @Override
    public abstract ElementID getElementId();

}
