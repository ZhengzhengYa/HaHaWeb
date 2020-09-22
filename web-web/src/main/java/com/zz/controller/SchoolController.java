package com.zz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.model.School;
import com.zz.model.SchoolExample;
import com.zz.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: ZhangZheng
 * @time: 2020/9/21 14:53
 */
@Controller
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @ResponseBody
    @RequestMapping("/getListSchool")
    public HashMap<String, Object> ListUser(@RequestParam int page,@RequestParam int limit){
        SchoolExample example = new SchoolExample();
        example.setOrderByClause("id ASC");
        PageHelper.startPage(page, limit);
        List<School> schools = schoolService.find(example);
        long count = schoolService.countByExample(example);
        PageInfo<School> schoolPageInfo = new PageInfo<>(schools,limit);
        List<School> list = schoolPageInfo.getList();
        HashMap<String, Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",list);
        map.put("code",0);
        return map;
    }
}
