package com.vue.element.admin.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司薪资项表
 * </p>
 *
 * @author rock.jiang
 * @since 2020-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompanySalaryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 薪资项编码
     */
    private String itemCode;

    /**
     * 薪资项名称
     */
    private String itemName;

    /**
     * 薪资项变量
     */
    private String itemVariant;

    /**
     * 薪资项别名
     */
    private String itemAlias;

    /**
     * 薪资项类型：1-文本项；2-输入项；3-计算项
     */
    private Integer itemType;

    /**
     * 数据类型：1-文本；2-整数；3-小数
     */
    private Integer dataType;

    /**
     * 数据长度
     */
    private Integer dataLength;

    /**
     * 小数位数
     */
    private Integer decimalDigits;

    /**
     * 进位方式：1-四舍五入；2-简单去位
     */
    private Integer roundingMode;

    /**
     * 录入公式
     */
    private String inputExpression;

    /**
     * 计算公式
     */
    private String computeExpression;

    /**
     * 显示顺序
     */
    private Integer displayOrder;

    /**
     * 计算顺序
     */
    private Integer computeOrder;

    /**
     * 病事假扣款基数项：1-是；0-否
     */
    private Integer sickLeaveDeductBaseItem;

    /**
     * 加班工资基数项：1-是；0-否
     */
    private Integer overtimeSalaryBaseItem;

    /**
     * 调整工资基数项：1-是；0-否
     */
    private Integer adjustSalaryBaseItem;

    /**
     * 薪资项来源：1-平台薪资项；2-自定义薪资项
     */
    private Integer itemSource;

    /**
     * 计入所得项收入：1-是；0-否
     */
    private Integer intoIncome;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否启用：1-启用；0-禁用
     */
    private Integer activeFlag;

    /**
     * 创建人ID
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人ID
     */
    private String updaterId;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 版本号
     */
    @Version
    private Integer version;


}
