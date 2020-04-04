package com.vue.element.alice.admin.result;

import lombok.Data;

/**
 * <p>Description: JSON Result </p>
 *
 * @author rock.jiang
 * Date 2020/01/03 15:15
 */
@Data
public class JSONResult {
    private Integer code;
    private String message;
    private Object data;

    public JSONResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public JSONResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
