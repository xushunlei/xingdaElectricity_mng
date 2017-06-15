package com.xinda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinda.dao.BranchMapper;
import com.xinda.entity.Branch;
@Service
public class BranchServiceImpl implements BranchService
{
	@Autowired
	private BranchMapper branchMapper;
	@Override
	public boolean saveBranch(Branch branch)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Branch> findAllBranch()
	{
		// TODO Auto-generated method stub
		List<Branch> list=branchMapper.selectBranchList();
		System.out.println("bs:"+list);
		return list;
	}

}
