package com.javatest.gremlin.client.repo.inet.edge.simple;


import com.baidu.spring.data.gremlin.annotation.Query;
import com.javatest.gremlin.client.domain.dao.inet.edge.simple.InterfaceHasIntf;
import com.javatest.gremlin.client.repo.SimpleTopoEdgeRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Repository
public interface InterfaceHasIntfRepository extends SimpleTopoEdgeRepository<InterfaceHasIntf> {

    @Override
    @Query("g.V().has('Interface', 'ifid', {pIfid}).outE('HAS_INF').where(inV().has('Interface', 'ifid', {cIfid}))")
    InterfaceHasIntf findByHeadAndTail(@Param("pIfid") final String pIfid, @Param("cIfid") final String cIfid);


    @Override
    @Query("g.V().has('Interface', 'ifid', {pIfid}).outE('HAS_INF')" +
            ".where(inV().has('Interface', 'ifid', {cIfid})).drop()")
    void deleteByHeadAndTail(@Param("pIfid") final String pIfid, @Param("cIfid") final String cIfid);
}
