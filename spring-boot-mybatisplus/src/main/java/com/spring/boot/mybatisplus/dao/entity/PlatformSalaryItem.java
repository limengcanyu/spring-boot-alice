package com.spring.boot.mybatisplus.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 平台薪资项定义信息表
 * </p>
 *
 * @author rock.jiang
 * @since 2020-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PlatformSalaryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

//    private String tenantId;

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
     * 原始公式
     */
    private String originalExpression;

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
     * 备注 
     */
    private String remark;

    /**
     * 是否启用：1-启用；0-禁用
     */
    private Boolean isActive;

    /**
     * 创建人
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String modifierId;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 版本号
     */
    @Version
    private Integer version;


}
