package com.xiaohuo.kyzp.module.member.enums;

import com.xiaohuo.kyzp.framework.common.exception.ErrorCode;

/**
 * Member 错误码枚举类
 * <p>
 * member 系统，使用 1-004-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 用户相关  1-004-001-000 ============
    ErrorCode USER_NOT_EXISTS = new ErrorCode(1_004_001_000, "用户不存在");
    ErrorCode USER_MOBILE_NOT_EXISTS = new ErrorCode(1_004_001_001, "手机号未注册用户");
    ErrorCode USER_MOBILE_USED = new ErrorCode(1_004_001_002, "修改手机失败，该手机号({})已经被使用");
    ErrorCode USER_EMAIL_USED = new ErrorCode(1_004_001_004, "修改邮箱失败，该邮箱({})已经被使用");

    // ========== AUTH 模块 1-004-003-000 ==========
    ErrorCode AUTH_LOGIN_BAD_CREDENTIALS = new ErrorCode(1_004_003_000, "登录失败，账号密码不正确");
    ErrorCode AUTH_LOGIN_USER_DISABLED = new ErrorCode(1_004_003_001, "登录失败，账号被禁用");
    ErrorCode AUTH_SOCIAL_USER_NOT_FOUND = new ErrorCode(1_004_003_005, "登录失败，解析不到三方登录信息");
    ErrorCode AUTH_MOBILE_USED = new ErrorCode(1_004_003_007, "手机号已经被使用");

}
