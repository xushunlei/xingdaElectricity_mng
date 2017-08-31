package com.xinda.entity;

public class User {
    private Integer userId;

    private String userAccount;

    private String userPassword;

    private String userCord;

    private String userName;

    private String userMobile;

    private Integer userRole;

    private Zone userZone;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserCord() {
        return userCord;
    }

    public void setUserCord(String userCord) {
        this.userCord = userCord == null ? null : userCord.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Zone getUserZone() {
		return userZone;
	}

    public void setUserZone(Zone userZone) {
		this.userZone = userZone;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword + ", userCord=" + userCord
				+ ", userName=" + userName + ", userMobile=" + userMobile
				+ ", userRole=" + userRole + ", userZone=" + userZone+ "]";
	}
}