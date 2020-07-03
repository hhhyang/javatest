package com.javatest.gremlin.client.repo.inet.vertex;


import com.baidu.spring.data.gremlin.annotation.Query;
import com.javatest.gremlin.client.domain.dao.inet.vertex.Netware;
import com.javatest.gremlin.client.repo.BaseStatusTopoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Netware Interface Repository
 *
 * @author yangshengbing@baidu.com
 */
@Repository
public interface NetwareRepository extends BaseStatusTopoRepository<Netware, Long> {

    @Query("g.V().hasLabel('Netware')")
    List<Netware> getAll();

    @Query("g.V().has('Netware', 'source', ':source')")
    List<Netware> findBySource(@Param("source") final String source);

    @Query("g.V().has('Netware', 'nwid', {nwid})")
    Netware findByNwid(@Param("nwid") final String nwid);

    @Query("g.V().has('Netware', 'mgmtIp', ':mgmtIp')")
    Netware findByElementId(@Param("mgmtIp") final String mgmtIp);

    @Query("g.V().has('SrLabel', 'labelid', {labelid}).out('')")
    Netware findBySrLabelId(@Param("labelid") final String labelid);

    @Query("g.V().has('Netware', 'nwid', {nwid}).drop()")
    void deleteByNwid(@Param("nwid") final String nwid);

    @Query("g.V().has('Netware', 'mgmtIp', {mgmtIp}).drop()")
    void deleteByElementId(@Param("mgmtIp") final String mgmtIp);

    @Query("g.V().has('Netware', 'nwid', {nwid}).out('HAS_INF').drop()")
    void deleteAllInterfaceByNwid(@Param("nwid") final String nwid);

    @Query("g.V().has('Netware', 'nwid', {nwid}).out('HAS_IP').drop()")
    void deleteAllIpByNwid(@Param("nwid") final String nwid);

    @Query("g.V().has('Netware', 'mgmtIp', {mgmtIp}).out('HAS_INF').drop()")
    void deleteAllInterfaceByElementId(@Param("mgmtIp") final String mgmtIp);

    @Query("g.V().has('Netware', 'mgmtIp', {mgmtIp}).out('HAS_IP').drop()")
    void deleteAllIpByElementId(@Param("mgmtIp") final String mgmtIp);

    @Query("g.V().has('Netware', 'mgmtIp', ':mgmtIp').property('operStatus', ':operStatus')" +
            ".property('timestamp', :timestamp)")
    Netware updateOperationStatus(@Param("mgmtIp") final String mgmtIp,
                                  @Param("operStatus") final String operStatus,
                                  @Param("timestamp") final Long timestamp);

    @Query(":script")
    List<Netware> findByScript(@Param("script") final String script);

}
