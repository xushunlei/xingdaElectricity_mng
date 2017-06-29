package com.xinda.service;

import java.util.List;

import com.xinda.entity.Branch;

public interface BranchService {
	/**
	 * 新增网点
	 * 
	 * @param branch
	 * @return
	 */
	public boolean saveBranch(Branch branch);

	/** 查询所有网点 */
	public List<Branch> findAllBranch();

	/** 查找所有网点名称 */
	public List<String> findAllBranchName();
}
