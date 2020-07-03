package com.javatest.gremlin.client.domain.dao;


import com.baidu.spring.data.gremlin.annotation.Index;
import com.javatest.gremlin.client.domain.status.LinkStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Link<HeadT extends TopoVertex, TailT extends TopoVertex>
        extends BaseStatusTopoEdge<String, HeadT, TailT> {


    @Index
    private String linkid;

    // 链路状态
    private LinkStatus status;

    @Override
    @JsonIgnore
    public String getOperationStatus() {
        return status.name();
    }

    @Override
    @JsonIgnore
    public String getElementId() {
        return linkid;
    }

}
