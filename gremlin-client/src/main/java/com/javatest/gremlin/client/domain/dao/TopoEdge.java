package com.javatest.gremlin.client.domain.dao;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
public interface TopoEdge<HeadT extends TopoVertex, TailT extends TopoVertex> extends TopoElement<String> {

    HeadT getHead();

    TailT getTail();

}
