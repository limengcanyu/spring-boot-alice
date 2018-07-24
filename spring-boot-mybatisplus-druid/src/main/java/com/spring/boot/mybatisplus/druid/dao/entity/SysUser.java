package com.spring.boot.mybatisplus.druid.dao.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Rock.Jiang
 * @since 2018-05-23
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 中文名称
     */
    @TableField("chinese_name")
    private String chineseName;
    /**
     * 性别：1-男，2-女
     */
    private Integer sex;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
