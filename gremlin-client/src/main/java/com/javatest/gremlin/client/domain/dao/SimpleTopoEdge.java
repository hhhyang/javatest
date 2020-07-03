package com.javatest.gremlin.client.domain.dao;

import com.baidu.spring.data.gremlin.annotation.EdgeHead;
import com.baidu.spring.data.gremlin.annotation.EdgeTail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTopoEdge<HeadT extends TopoVertex, TailT extends TopoVertex>
        extends SimpleTopoElement<String> implements TopoEdge<HeadT, TailT> {

    @EdgeHead
    private HeadT head;

    @EdgeTail
    private TailT tail;

    public SimpleTopoEdge(final String gid, final HeadT head, final TailT tail) {
        super(gid);

        this.head = head;
        this.tail = tail;
    }

}
