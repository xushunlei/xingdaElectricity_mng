package com.xinda.dao;

import com.xinda.entity.Branch;

public interface BranchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Branch record);

    int insertSelective(Branch record);

    Branch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Branch record);

    int updateByPrimaryKey(Branch record);
}