package com.cetc.bigdata.analysis.web.dao;

import com.cetc.bigdata.analysis.web.pojo.Role;
import com.cetc.bigdata.analysis.web.pojo.RoleRes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    int save(Role role);

    List<Role> getRoleList(String userId);

    int update(Role role);

    List<RoleRes> getRoleResList();

    int saveRoleRes(List<RoleRes> list);

    int deleteRoleRes(String roleId);

    List<Long> getRoleResByResIds(String userId, String resType);

    Role getRole(String roleId);

    int deleteRole(String roleId);
}
