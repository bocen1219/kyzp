package com.xiaohuo.kyzp.module.system.dal.mysql.mail;

import com.xiaohuo.kyzp.framework.common.pojo.PageResult;
import com.xiaohuo.kyzp.framework.mybatis.core.mapper.BaseMapperX;
import com.xiaohuo.kyzp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xiaohuo.kyzp.module.system.controller.admin.mail.vo.account.MailAccountPageReqVO;
import com.xiaohuo.kyzp.module.system.dal.dataobject.mail.MailAccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailAccountMapper extends BaseMapperX<MailAccountDO> {

    default PageResult<MailAccountDO> selectPage(MailAccountPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<MailAccountDO>()
                .likeIfPresent(MailAccountDO::getMail, pageReqVO.getMail())
                .likeIfPresent(MailAccountDO::getUsername , pageReqVO.getUsername())
                .orderByDesc(MailAccountDO::getId));
    }

}
