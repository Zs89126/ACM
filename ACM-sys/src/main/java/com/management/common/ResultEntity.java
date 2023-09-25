package com.management.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *通用返回类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity {
    /**
     * 状态码
     */
    private Integer code;

    /**
     *提示
     */
    private String message;

    /**
     * 数据
     */
    private Object data;
}
