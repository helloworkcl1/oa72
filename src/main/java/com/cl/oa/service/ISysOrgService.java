package com.cl.oa.service;

import com.cl.oa.common.Page;
import com.cl.oa.common.SysResult;
import com.cl.oa.entity.SysOrg;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISysOrgService extends IBaseService<SysOrg> {
    PageInfo<SysOrg> getPage(Page page);

    List<SysOrg> getOrgList();

    SysResult updateFlagByOrgId(Long orgId);

    SysResult updateFlagByIdList(List<Long> idList);

    PageInfo<SysOrg> selectByCondition(SysOrg sysOrg, Page page);
}
