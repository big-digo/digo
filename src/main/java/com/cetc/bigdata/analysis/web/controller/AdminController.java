package com.cetc.bigdata.analysis.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cetc.bigdata.analysis.web.service.AdminSerivce;
import com.cetc.bigdata.analysis.web.service.AuthenticationService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminSerivce adminSerivce;

//    @Autowired
//    private UserDao userDao;

//    @Autowired
//    private RoleDao roleDao;

    @Value("${admin_user_id}")
    private String adminUserId;

//    @Autowired
//    private MenuDao menuDao;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/saveNewUser")
    public String saveNewUser(@RequestParam(name = "user") String user) {
        JSONObject jsonObject = JSONObject.parseObject(user);
        return adminSerivce.addUser(UUID.randomUUID().toString(), jsonObject.getString("loginName"), jsonObject.getString("userName"), jsonObject.getString("userPassword"));
    }

    @RequestMapping(value = "/updateUser")
    public String updateUser(@RequestParam(name = "user") String user) {
        JSONObject jsonObject = JSONObject.parseObject(user);
        return adminSerivce.updateUser(jsonObject.getString("userId"), jsonObject.getString("loginName"), jsonObject.getString("userName"), jsonObject.getString("userPassword"));
    }

//    @RequestMapping(value = "/getUserList")
//    public List<User> getUserList() {
//        List<User> list = userDao.getUserList();
//        return list;
//    }

    @RequestMapping(value = "/saveRole")
    public String saveRole(@RequestParam(name = "role") String role) {
        JSONObject jsonObject = JSONObject.parseObject(role);
        return adminSerivce.addRole(UUID.randomUUID().toString(), jsonObject.getString("roleName"), jsonObject.getString("userId"));
    }

    @RequestMapping(value = "/updateRole")
    public String updateRole(@RequestParam(name = "role") String role) {
        JSONObject jsonObject = JSONObject.parseObject(role);
        return adminSerivce.updateRole(jsonObject.getString("roleId"), jsonObject.getString("roleName"), jsonObject.getString("userId"));
    }

    @RequestMapping(value = "/deleteRole")
    public String deleteRole(@RequestParam(name = "roleId") String roleId) {
        return adminSerivce.deleteRole(roleId);
    }

//    @RequestMapping(value = "/getRoleList")
//    public List<Role> getRoleList() {
//        List<Role> list = roleDao.getRoleList(authenticationService.getCurrentUser().getUserId());
//        return list;
//    }

    @RequestMapping(value = "/updateUserRole")
    public String updateUserRole(@RequestParam(name = "userIdArr") String userIdArr, @RequestParam(name = "roleIdArr") String roleIdArr) {
        return adminSerivce.updateUserRole(
                JSONArray.parseArray(userIdArr).toArray(new String[]{}),
                JSONArray.parseArray(roleIdArr).toArray(new String[]{}),
                authenticationService.getCurrentUser().getUserId()
        );
    }

//    @RequestMapping(value = "/getUserRoleList")
//    public List<UserRole> getUserRoleList() {
//        List<UserRole> list = userDao.getUserRoleList();
//        return list;
//    }

//    @RequestMapping(value = "/getRoleResList")
//    public List<RoleRes> getRoleResList() {
//        List<RoleRes> list = roleDao.getRoleResList();
//        return list;
//    }

    @RequestMapping(value = "/updateRoleRes")
    public String updateRoleRes(@RequestParam(name = "roleIdArr") String roleIdArr, @RequestParam(name = "resIdArr") String resIdArr) {
        return adminSerivce.updateRoleRes(JSONArray.parseArray(roleIdArr).toArray(new String[]{}), resIdArr);
    }

    @RequestMapping(value = "/isAdmin")
    public boolean isAdmin() {
        return adminUserId.equals(authenticationService.getCurrentUser().getUserId());
    }

//    @RequestMapping(value = "/isConfig")
//    public boolean isConfig(@RequestParam(name = "type") String type) {
//        String userid = authenticationService.getCurrentUser().getUserId();
//        if (userid.equals(adminUserId)) {
//            return true;
//        } else if (type.equals("widget")) {
//            List<Long> menuIdList = menuDao.getMenuIdByUserRole(userid);
//            if (menuIdList.contains(1L) && menuIdList.contains(4L)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
