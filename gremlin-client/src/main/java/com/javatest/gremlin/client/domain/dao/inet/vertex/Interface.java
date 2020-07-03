package com.javatest.gremlin.client.domain.dao.inet.vertex;

import com.baidu.spring.data.gremlin.annotation.Index;
import com.baidu.spring.data.gremlin.annotation.Vertex;
import com.javatest.gremlin.client.domain.dao.BaseStatusTopoVertex;
import com.javatest.gremlin.client.domain.status.InterfaceStatus;
import com.javatest.gremlin.client.domain.type.InterfaceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 接口Entity
 *
 * @author yangshengbing@baidu.com
 */
@Vertex(label = "Interface")
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Interface extends BaseStatusTopoVertex<String> {

    /*
     * 接口对象列表的键值定义.
     * 使用接口的ifid作为键值.
     * ifid的生成规则："if+warehouse+manageip+slot+child-card+index".
     */
    @Index(unique = true)
    private String ifid;

    // 接口名
    private String name;
    // slot编号
    private Long slot;
    // 子卡编号
    private Long childCard;
    // 接口index
    private Long index;
    // 接口类型
    private InterfaceType interfaceType;
    // 接口描述
    private String description;
    // 接口带宽
    private Long bandwidth;
    // portnum
    private Long portNum;
    // 接口cost值
    private Integer cost;
    // 接口管理状态
    private InterfaceStatus adminStatus;
    // 接口工作状态
    private InterfaceStatus operStatus;

    @Override
    @JsonIgnore
    public String getElementId() {
        return ifid;
    }

    @Override
    @JsonIgnore
    public String getOperationStatus() {
        return operStatus.name();
    }
}
