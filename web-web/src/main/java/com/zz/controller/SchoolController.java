package com.zz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.model.School;
import com.zz.model.SchoolExample;
import com.zz.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ResponseBody
    @RequestMapping("/insertSchool.do")
    public HashMap<String, Object> insert(@RequestBody HashMap<String, String> data){
        HashMap<String, Object> map = new HashMap<>();
        if (data != null) {
            String seq = data.get("seq");
            String score = data.get("score");
            String name = data.get("name");
            String level = data.get("level");
            String remark = data.get("remark");
            School school = new School();
            school.setSeq(seq);
            school.setScore(score);
            school.setName(name);
            school.setLevel(level);
            school.setRemark(remark);
            schoolService.insert(school);
            map.put("msg", "添加成功！");
            map.put("code", 0);
        }
        else {
            map.put("msg", "数据异常，添加失败！");
            map.put("code", 0);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/deleteSchool.do")
    public HashMap<String, Object> insert(@RequestParam int id){
        System.out.println(id);
        HashMap<String, Object> map = new HashMap<>();
        if (id > 0) {
            schoolService.deleteByPrimaryKey(id);
            map.put("msg","删除成功!");
            map.put("code",0);
        }
        else {
            map.put("msg","数据异常，删除失败!");
            map.put("code",0);
        }
        return map;
    }


    @ResponseBody
    @RequestMapping("/editSchool.do")
    public HashMap<String, Object> edit(@RequestBody HashMap<String, String> data){
        HashMap<String, Object> map = new HashMap<>();
        if (data != null) {
            String id = data.get("id");
            String seq = data.get("seq");
            String score = data.get("score");
            String name = data.get("name");
            String level = data.get("level");
            String remark = data.get("remark");
            System.out.println("id="+id+" seq="+seq+" score="+score+" name="+name+" level="+level+ " remark="+remark);
            School school = new School();
            school.setSeq(seq);
            school.setScore(score);
            school.setName(name);
            school.setLevel(level);
            school.setRemark(remark);
            SchoolExample example = new SchoolExample();
            example.createCriteria().andIdEqualTo(Integer.parseInt(id));
            schoolService.updateByExampleSelective(school,example);
            map.put("msg", "修改成功！");
            map.put("code", 0);
        }
        else {
            map.put("msg", "数据异常，修改失败！");
            map.put("code", 0);
        }
        return map;
    }
}
