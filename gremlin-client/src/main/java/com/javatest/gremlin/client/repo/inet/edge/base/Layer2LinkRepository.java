package com.javatest.gremlin.client.repo.inet.edge.base;


import com.baidu.spring.data.gremlin.annotation.Query;
import com.javatest.gremlin.client.domain.dao.inet.edge.base.Layer2Link;
import com.javatest.gremlin.client.repo.LinkRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Layer 2 Link Interface Repository
 *
 * @author yangshengbing@baidu.com
 */
@Repository
public interface Layer2LinkRepository extends LinkRepository<Layer2Link> {

    @Query("g.E().hasLabel('L2_LINK')")
    List<Layer2Link> getAll();

    @Query("g.V().has('L2_LINK', 'source', {source})")
    List<Layer2Link> findBySource(@Param("source") final String source);

    @Query("g.E().hasLabel('L2_LINK').has('linkid', {linkid}).drop()")
    void deleteByLinkId(@Param("linkid") final String linkid);

    @Query("g.E().hasLabel('L2_LINK').has('linkid', {linkid}).drop()")
    void deleteByElementId(@Param("linkid") final String linkid);

    @Override
    @Query("g.E().has('L2_LINK', 'linkid', {linkid}).property('status', {status}).property('timestamp', {timestamp})")
    Layer2Link updateOperationStatus(@Param("linkid") final String linkid,
                                     @Param("status") final String status,
                                     @Param("timestamp") final Long timestamp);

    @Query("g.E().hasLabel('L2_LINK').has('linkid', {linkid})")
    Layer2Link findByLinkId(@Param("linkid") final String linkid);

    @Query("g.E().hasLabel('L2_LINK').has('linkid', {linkid})")
    Layer2Link findByElementId(@Param("linkid") final String linkid);

    @Query("g.V().has('Netware', 'nwid', {nwid}).outE('L2_LINK').has('locifid', {locifid}).drop()")
    void deleteOutEdgeByLocInfo(@Param("nwid") final String nwid, @Param("locifid") final String locifid);

    @Query("g.V().has('Netware', 'nwid', {nwid}).outE('L2_LINK').has('locifid', {locifid})")
    List<Layer2Link> findByEdgeHead(@Param("nwid") final String nwid, @Param("locifid") final String locifid);

    @Query("g.V().has('Netware', 'nwid', {nwid}).inE('L2_LINK').has('remifid', {remifid})")
    List<Layer2Link> findByEdgeTail(@Param("nwid") final String nwid, @Param("remifid") final String remifid);

    @Query("g.V().has('Netware', 'nwid', {nwid}).inE('L2_LINK').has('remifid', {remifid}).drop()")
    void deleteInEdgeByRemInfo(@Param("nwid") final String nwid, @Param("remifid") final String remifid);

    @Query("g.E().hasLabel('L2_LINK').has('locifid', {locifid}).property('bandwidth', {bandwidth})")
    void updateBandwidthByLocIfid(@Param("locifid") final String locifid, @Param("bandwidth") final Long bandwidth);

    @Query("{script}")
    List<Layer2Link> findByScript(@Param("script") final String script);

}
