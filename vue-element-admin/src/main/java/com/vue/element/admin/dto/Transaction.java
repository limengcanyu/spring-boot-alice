package com.vue.element.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/5/30 0:29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    private String order_no;
    private LocalDateTime timestamp;
    private String username;
    private Double price;
    private String status;
}
