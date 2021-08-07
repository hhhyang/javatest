package com.baidu.sdn.topo.analyzer.core.domain.vo;

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
public class ApiResponse {

    private String code;

    private String message;
}
