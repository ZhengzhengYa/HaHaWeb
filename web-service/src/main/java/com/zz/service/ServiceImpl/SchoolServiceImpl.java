package com.zz.service.ServiceImpl;

import com.zz.dao.SchoolMapper;
import com.zz.model.School;
import com.zz.model.SchoolExample;
import com.zz.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: ZhangZheng
 * @time: 2020/9/21 14:51
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolMapper schoolMapper;
    @Override
    public List<School> find(SchoolExample example) {
        return schoolMapper.selectByExample(example);
    }

    @Override
    public long countByExample(SchoolExample example) {
        return schoolMapper.countByExample(example);
    }
}
