package com.zz.service;

import com.zz.model.School;
import com.zz.model.SchoolExample;

import java.util.List;

/**
 * @description:
 * @author: ZhangZheng
 * @time: 2020/9/21 14:49
 */
public interface SchoolService {
    List<School> find(SchoolExample example);
    long countByExample(SchoolExample example);
}
