package com.cetc.bigdata.analysis.web.dao;

import com.cetc.bigdata.analysis.web.pojo.User;
import com.cetc.bigdata.analysis.web.pojo.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    int save(User user);

    List<User> getUserList();

    int update(User user);

    int saveUserRole(List<UserRole> list);

    int deleteUserRole(Map<String, Object> param);

    List<UserRole> getUserRoleList();

    User getUserByLoginName(String loginName);

    int saveNewUser(String userId, String user_name, String loginName);

    int updateUserPassword(String userId, String passowrd, String newPassword);

    int deleteUserRoleByRoleId(String roleId);
}
