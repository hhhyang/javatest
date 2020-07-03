package com.javatest.gremlin.client.repo.inet.edge.simple;


import com.baidu.spring.data.gremlin.annotation.Query;
import com.javatest.gremlin.client.domain.dao.inet.edge.simple.NetwareHasIntf;
import com.javatest.gremlin.client.repo.SimpleTopoEdgeRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Repository
public interface NetwareHasIntfRepository extends SimpleTopoEdgeRepository<NetwareHasIntf> {

    @Override
    @Query("g.V().has('Netware', 'nwid', {nwid}).outE('HAS_INF').where(inV().has('Interface', 'ifid', {ifid}))")
    NetwareHasIntf findByHeadAndTail(@Param("nwid") final String nwid, @Param("ifid") final String ifid);


    @Override
    @Query("g.V().has('Netware', 'nwid', {nwid}).outE('HAS_INF').where(inV().has('Interface', 'ifid', {ifid})).drop()")
    void deleteByHeadAndTail(@Param("nwid") final String nwid, @Param("ifid") final String ifid);

}
