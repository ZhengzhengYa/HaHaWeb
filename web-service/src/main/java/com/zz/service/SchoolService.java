package com.zz.service;

import com.zz.model.School;
import com.zz.model.SchoolExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: ZhangZheng
 * @time: 2020/9/21 14:49
 */
public interface SchoolService {
    List<School> find(SchoolExample example);
    long countByExample(SchoolExample example);
    int insert(School record);
    int deleteByPrimaryKey(Integer id);
    int updateByExampleSelective(@Param("record") School record, @Param("example") SchoolExample example);

}
