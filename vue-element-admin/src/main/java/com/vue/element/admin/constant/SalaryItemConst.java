package com.vue.element.admin.constant;

import com.vue.element.admin.dto.SalaryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author rock.jxf
 * @date 2020/6/4 22:14
 */
public class SalaryItemConst {
    //薪资项类型：1-固定工资项；2-绩效，奖金，经济补偿；3-浮动工资项；4-公司福利项；5-税后工资项
    public static final Map<String, Object> salaryInputItemBigTypeList = new HashMap<>();

    static {
        salaryInputItemBigTypeList.put("itemBigType", 1);
        salaryInputItemBigTypeList.put("itemBigTypeName", "固定工资项");
        List<SalaryItem> itemList = new ArrayList<>();
        itemList.add(new SalaryItem("item_001", "固定工资项001"));
        itemList.add(new SalaryItem("item_002", "固定工资项002"));
        itemList.add(new SalaryItem("item_003", "固定工资项003"));
        salaryInputItemBigTypeList.put("itemList", itemList);

        salaryInputItemBigTypeList.put("itemBigType", 2);
        salaryInputItemBigTypeList.put("itemBigTypeName", "绩效，奖金，经济补偿");
        itemList = new ArrayList<>();
        itemList.add(new SalaryItem("item_004", "绩效项004"));
        itemList.add(new SalaryItem("item_005", "奖金项005"));
        itemList.add(new SalaryItem("item_006", "补偿项006"));
        salaryInputItemBigTypeList.put("itemList", itemList);

        salaryInputItemBigTypeList.put("itemBigType", 3);
        salaryInputItemBigTypeList.put("itemBigTypeName", "浮动工资项");
        itemList = new ArrayList<>();
        itemList.add(new SalaryItem("item_007", "浮动项007"));
        itemList.add(new SalaryItem("item_008", "浮动项008"));
        itemList.add(new SalaryItem("item_009", "浮动项009"));
        salaryInputItemBigTypeList.put("itemList", itemList);
    }
}
