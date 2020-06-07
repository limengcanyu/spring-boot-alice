package com.spring.boot.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/5/29 23:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;
}
