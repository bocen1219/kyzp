package com.xiaohuo.kyzp.module.member.convert.user;

import com.xiaohuo.kyzp.framework.common.pojo.PageResult;
import com.xiaohuo.kyzp.module.member.controller.admin.user.vo.MemberUserRespVO;
import com.xiaohuo.kyzp.module.member.controller.admin.user.vo.MemberUserUpdateReqVO;
import com.xiaohuo.kyzp.module.member.controller.app.user.vo.AppMemberUserInfoRespVO;
import com.xiaohuo.kyzp.module.member.dal.dataobject.user.MemberUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberUserConvert {

    MemberUserConvert INSTANCE = Mappers.getMapper(MemberUserConvert.class);

    AppMemberUserInfoRespVO convert(MemberUserDO bean);

    MemberUserDO convert(MemberUserUpdateReqVO bean);

    PageResult<MemberUserRespVO> convertPage(PageResult<MemberUserDO> page);

    MemberUserRespVO convert03(MemberUserDO bean);

}
