package com.vue.element.admin;

import com.vue.element.admin.service.ImportCompanySalaryItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description:
 *
 * @author rock
 * time 2020/7/3 0003 10:08
 */
@SpringBootTest
public class ImportCompanySalaryItemServiceTest {
    @Autowired
    private ImportCompanySalaryItemService importCompanySalaryItemService;

    @Test
    public void importCompanySalaryItem() {
        String fileName = "D:/xxx.xls";
        importCompanySalaryItemService.importCompanySalaryItem(fileName);
    }
}
