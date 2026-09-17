package com.xiaohuo.kyzp.module.member.convert.auth;

import com.xiaohuo.kyzp.module.member.controller.app.auth.vo.*;
import com.xiaohuo.kyzp.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import com.xiaohuo.kyzp.module.member.controller.app.user.vo.AppMemberUserResetPasswordReqVO;
import com.xiaohuo.kyzp.framework.common.biz.system.oauth2.dto.OAuth2AccessTokenRespDTO;
import com.xiaohuo.kyzp.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import com.xiaohuo.kyzp.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import com.xiaohuo.kyzp.module.system.api.sms.dto.code.SmsCodeValidateReqDTO;
import com.xiaohuo.kyzp.module.system.api.social.dto.SocialUserBindReqDTO;
import com.xiaohuo.kyzp.module.system.api.social.dto.SocialUserUnbindReqDTO;
import com.xiaohuo.kyzp.module.system.api.social.dto.SocialWxJsapiSignatureRespDTO;
import com.xiaohuo.kyzp.module.system.enums.sms.SmsSceneEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, AppAuthSocialLoginReqVO reqVO);
    SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO);

    SmsCodeSendReqDTO convert(AppAuthSmsSendReqVO reqVO);
    SmsCodeUseReqDTO convert(AppMemberUserResetPasswordReqVO reqVO, SmsSceneEnum scene, String usedIp);
    SmsCodeUseReqDTO convert(AppAuthSmsLoginReqVO reqVO, Integer scene, String usedIp);

    AppAuthLoginRespVO convert(OAuth2AccessTokenRespDTO bean, String openid);

    SmsCodeValidateReqDTO convert(AppAuthSmsValidateReqVO bean);

    SocialWxJsapiSignatureRespDTO convert(SocialWxJsapiSignatureRespDTO bean);

}
