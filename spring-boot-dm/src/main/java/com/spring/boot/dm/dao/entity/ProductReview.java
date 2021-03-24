package com.spring.boot.dm.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author rock.jiang
 * @since 2021-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PRODUCT_REVIEW")
public class ProductReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("PRODUCT_REVIEWID")
    private Integer productReviewid;

    @TableField("PRODUCTID")
    private Integer productid;

    @TableField("NAME")
    private String name;

    @TableField("REVIEWDATE")
    private Date reviewdate;

    @TableField("EMAIL")
    private String email;

    @TableField("RATING")
    private Integer rating;

    @TableField("COMMENTS")
    private String comments;

    @TableField("VERSION")
    private Integer version;


}
