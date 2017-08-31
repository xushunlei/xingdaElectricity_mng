package com.xinda.entity2;

import com.xinda.entity.User;

public class Branch {
    private Integer branchId;

    private String branchName;

    private String branchNumber;

    private String branchAddress;

    private User branchManager;//需要修改类型为User

    private String branchHost;

    private Integer branchPort;

	public Integer getBranchId()
	{
		return branchId;
	}

	public void setBranchId(Integer branchId)
	{
		this.branchId = branchId;
	}

	public String getBranchName()
	{
		return branchName;
	}

	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}

	public String getBranchNumber()
	{
		return branchNumber;
	}

	public void setBranchNumber(String branchNumber)
	{
		this.branchNumber = branchNumber;
	}

	public String getBranchAddress()
	{
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress)
	{
		this.branchAddress = branchAddress;
	}

	public User getBranchManager()
	{
		return branchManager;
	}

	public void setBranchManager(User branchManager)
	{
		this.branchManager = branchManager;
	}

	public String getBranchHost()
	{
		return branchHost;
	}

	public void setBranchHost(String branchHost)
	{
		this.branchHost = branchHost;
	}

	public Integer getBranchPort()
	{
		return branchPort;
	}

	public void setBranchPort(Integer branchPort)
	{
		this.branchPort = branchPort;
	}

	@Override
	public String toString()
	{
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", branchNumber=" + branchNumber + ", branchAddress=" + branchAddress + ", branchManager=" + branchManager + ", branchHost=" + branchHost + ", branchPort=" + branchPort + "]";
	}

    
}