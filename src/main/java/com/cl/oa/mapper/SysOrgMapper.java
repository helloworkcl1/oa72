package com.cl.oa.mapper;

import com.cl.oa.entity.SysOrg;
import com.cl.oa.dao.IBaseDao;

import java.util.List;

public interface SysOrgMapper extends IBaseDao<SysOrg> {

    List<SysOrg> getOrgList();

    int queryCountByOrgParentId(Long orgId);

    int queryCountByIdList(List<Long> idList);

    int batchUpdateFlagByIdList(List<Long> idList);

    List<SysOrg> selectByCondition(SysOrg sysOrg);
}