package com.xiaohuo.kyzp.module.member.dal.dataobject.user;

import com.xiaohuo.kyzp.framework.common.enums.CommonStatusEnum;
import com.xiaohuo.kyzp.framework.common.enums.TerminalEnum;
import com.xiaohuo.kyzp.framework.tenant.core.db.TenantBaseDO;
import com.xiaohuo.kyzp.module.system.enums.common.SexEnum;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

/**
 * 会员用户 DO
 *
 * uk_mobile 索引：基于 {@link #mobile} 字段
 *
 * @author 芋道源码
 */
@TableName(value = "member_user", autoResultMap = true)
@KeySequence("member_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUserDO extends TenantBaseDO {

    // ========== 账号信息 ==========

    /**
     * 用户ID
     */
    @TableId
    private Long id;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 加密后的密码
     *
     * 因为目前使用 {@link BCryptPasswordEncoder} 加密器，所以无需自己处理 salt 盐
     */
    private String password;
    /**
     * 帐号状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 注册 IP
     */
    private String registerIp;
    /**
     * 注册终端
     * 枚举 {@link TerminalEnum}
     */
    private Integer registerTerminal;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    // ========== 基础信息 ==========

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 真实名字
     */
    private String name;
    /**
     * 性别
     *
     * 枚举 {@link SexEnum}
     */
    private Integer sex;
    /**
     * 出生日期
     */
    private LocalDateTime birthday;

}
