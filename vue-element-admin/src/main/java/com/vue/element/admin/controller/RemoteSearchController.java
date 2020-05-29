package com.vue.element.admin.controller;

import com.vue.element.admin.entity.Result;
import com.vue.element.admin.entity.Transaction;
import com.vue.element.admin.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/5/30 0:27
 */
@Slf4j
@RestController
public class RemoteSearchController {
    @GetMapping("/vue-element-admin/transaction/list")
    public Result list() {
        log.debug("====== list");

        System.out.println("====== list");

        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction("order_001", LocalDateTime.now(), "rock", 20.00, "success"));
        list.add(new Transaction("order_002", LocalDateTime.now(), "jessica", 21.00, "pending"));

        Map<String, Object> map = new HashMap<>();
        map.put("total", 2);
        map.put("items", list);

        return new Result(20000, null, map);
    }

}
