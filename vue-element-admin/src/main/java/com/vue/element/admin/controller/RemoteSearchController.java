package com.vue.element.admin.controller;

import com.vue.element.admin.dto.Result;
import com.vue.element.admin.dto.Transaction;
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

        List<Transaction> list = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            list.add(new Transaction("order_00" + i, LocalDateTime.now(), "user0" + i, 20.00 + i, i % 2 == 0 ? "success" : "pending"));
        }

        for (int i = 10; i <= 20; i++) {
            list.add(new Transaction("order_0" + i, LocalDateTime.now(), "user" + i, 20.00 + i, i % 2 == 0 ? "success" : "pending"));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("total", 20);
        map.put("items", list);

        return new Result(20000, null, map);
    }

}
