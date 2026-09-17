package com.xiaohuo.kyzp.module.member.dal.mysql.user;

import com.xiaohuo.kyzp.framework.common.pojo.PageResult;
import com.xiaohuo.kyzp.framework.mybatis.core.mapper.BaseMapperX;
import com.xiaohuo.kyzp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xiaohuo.kyzp.module.member.controller.admin.user.vo.MemberUserPageReqVO;
import com.xiaohuo.kyzp.module.member.dal.dataobject.user.MemberUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 会员 User Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MemberUserMapper extends BaseMapperX<MemberUserDO> {

    default MemberUserDO selectByMobile(String mobile) {
        return selectOne(MemberUserDO::getMobile, mobile);
    }

    default MemberUserDO selectByEmail(String email) {
        return selectOne(MemberUserDO::getEmail, email);
    }

    default List<MemberUserDO> selectListByNicknameLike(String nickname) {
        return selectList(new LambdaQueryWrapperX<MemberUserDO>()
                .likeIfPresent(MemberUserDO::getNickname, nickname));
    }

    default PageResult<MemberUserDO> selectPage(MemberUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberUserDO>()
                .likeIfPresent(MemberUserDO::getMobile, reqVO.getMobile())
                .likeIfPresent(MemberUserDO::getEmail, reqVO.getEmail())
                .betweenIfPresent(MemberUserDO::getLoginDate, reqVO.getLoginDate())
                .likeIfPresent(MemberUserDO::getNickname, reqVO.getNickname())
                .betweenIfPresent(MemberUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberUserDO::getId));
    }

}
