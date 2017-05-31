package com.cetc.bigdata.analysis.web.pojo;

public class RoleRes {
    private Long roleResId;
    private String roleId;
    private Long resId;
    private String resType;

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public Long getRoleResId() {
        return roleResId;
    }

    public void setRoleResId(Long roleResId) {
        this.roleResId = roleResId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }
}
