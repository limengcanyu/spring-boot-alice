package com.vue.element.admin.controller;

import com.vue.element.admin.constant.SalaryItemConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>Description: Salary Input Controller</p>
 *
 * @author rock
 * date 2019/06/23
 */
@Slf4j
@RestController
public class SalaryInputController {
    /**
     * localhost:8080/getSalaryInputItemBigTypeList
     *
     * @return
     */
    @RequestMapping("/getSalaryInputItemBigTypeList")
    public Map<String, Object> getSalaryInputItemBigTypeList() {
        return SalaryItemConst.salaryInputItemBigTypeList;
    }
}
