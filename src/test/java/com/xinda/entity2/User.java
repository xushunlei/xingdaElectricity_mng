package com.xinda.entity2;

public class User {
    private Integer userId;

    private String userAccount;

    private String userPassword;

    private String userName;

    private Integer userRole;

    private String userMobile;

    private Double userBalance;

    private String userIdcard;

    private String userEmail;

    private String userAddress;

    private Byte userStatus;

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public String getUserAccount()
	{
		return userAccount;
	}

	public void setUserAccount(String userAccount)
	{
		this.userAccount = userAccount;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Integer getUserRole()
	{
		return userRole;
	}

	public void setUserRole(Integer userRole)
	{
		this.userRole = userRole;
	}

	public String getUserMobile()
	{
		return userMobile==null?"":userMobile;
	}

	public void setUserMobile(String userMobile)
	{
		this.userMobile = userMobile;
	}

	public Double getUserBalance()
	{
		return userBalance;
	}

	public void setUserBalance(Double userBalance)
	{
		this.userBalance = userBalance;
	}

	public String getUserIdcard()
	{
		return userIdcard==null?"":userIdcard;
	}

	public void setUserIdcard(String userIdcard)
	{
		this.userIdcard = userIdcard;
	}

	public String getUserEmail()
	{
		return userEmail==null?"":userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getUserAddress()
	{
		return userAddress==null?"":userAddress;
	}

	public void setUserAddress(String userAddress)
	{
		this.userAddress = userAddress;
	}

	public Byte getUserStatus()
	{
		return userStatus;
	}

	public void setUserStatus(Byte userStatus)
	{
		this.userStatus = userStatus;
	}

	@Override
	public String toString()
	{
		return "User [userId=" + userId + ", userAccount=" + userAccount + ", userPassword=" + userPassword + ", userName=" + userName + ", userRole=" + userRole + ", userMobile=" + userMobile + ", userBalance=" + userBalance + ", userIdcard=" + userIdcard + ", userEmail=" + userEmail + ", userAddress=" + userAddress + ", userStatus=" + userStatus + "]";
	}
}