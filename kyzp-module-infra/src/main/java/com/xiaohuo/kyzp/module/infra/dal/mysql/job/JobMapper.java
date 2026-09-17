package com.xiaohuo.kyzp.module.infra.dal.mysql.job;

import com.xiaohuo.kyzp.framework.common.pojo.PageResult;
import com.xiaohuo.kyzp.framework.mybatis.core.mapper.BaseMapperX;
import com.xiaohuo.kyzp.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.xiaohuo.kyzp.module.infra.controller.admin.job.vo.job.JobPageReqVO;
import com.xiaohuo.kyzp.module.infra.dal.dataobject.job.JobDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface JobMapper extends BaseMapperX<JobDO> {

    default JobDO selectByHandlerName(String handlerName) {
        return selectOne(JobDO::getHandlerName, handlerName);
    }

    default PageResult<JobDO> selectPage(JobPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JobDO>()
                .likeIfPresent(JobDO::getName, reqVO.getName())
                .eqIfPresent(JobDO::getStatus, reqVO.getStatus())
                .likeIfPresent(JobDO::getHandlerName, reqVO.getHandlerName())
                .orderByDesc(JobDO::getId));
    }

}
