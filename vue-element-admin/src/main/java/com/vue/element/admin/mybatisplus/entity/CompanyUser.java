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
 * 公司用户表
 * </p>
 *
 * @author rock.jiang
 * @since 2020-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CompanyUser implements Serializable {

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
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 雇员ID
     */
    private String employeeId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * email
     */
    private String email;

    /**
     * 描述
     */
    private String introduction;

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
