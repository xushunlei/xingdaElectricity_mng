package com.xinda.entity2;

public class Role {
    private Integer roleId;

    private String roleName;

    private Integer roleStatus;

    public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }
	@Override
	public String toString()
	{
		return "Role [id=" + roleId + ", roleName=" + roleName + ", roleStatus=" + roleStatus + "]";
	}
}