package com.javatest.gremlin.client.repo.inet.vertex;


import com.baidu.spring.data.gremlin.annotation.Query;
import com.javatest.gremlin.client.domain.dao.inet.vertex.Interface;
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
public interface InterfaceRepository extends BaseStatusTopoRepository<Interface, Long> {

    @Query("g.V().has('Interface', 'ifid', {ifid})")
    Interface findByIfid(@Param("ifid") String ifid);

    @Query("g.V().has('Interface', 'ifid', {ifid})")
    Interface findByElementId(@Param("ifid") String ifid);

    @Query("g.V().has('Netware', 'mgmtIp', {mgmtIp}).outE('HAS_INF').inV()")
    List<Interface> findAllByNetwareElementId(@Param("mgmtIp") String mgmtIp);

    @Query("g.V().has('Ip', 'ipid', {ipid}).outE('BELONGTO_INF').inV()")
    Interface findByIpid(@Param("ipid") String ipid);

    @Query("g.V().has('Interface', 'ifid', {ifid}).out('HAS_INF')")
    List<Interface> findByParentIfid(@Param("ifid") String ifid);

    @Query("g.V().has('Interface', 'ifid', {ifid}).property('operStatus', {operStatus})" +
            ".property('timestamp', {timestamp})")
    Interface updateOperationStatus(@Param("ifid") final String ifid,
                                    @Param("operStatus") final String operStatus,
                                    @Param("timestamp") final Long timestamp);

}