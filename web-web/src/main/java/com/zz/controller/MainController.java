package com.zz.controller;

import com.zz.model.User;
import com.zz.model.UserExample;
import com.zz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: ZhangZheng
 * @time: 2020/9/10 11:53
 */
@Controller
public class MainController {
    @Autowired
    UserService userService;
    @ResponseBody
    @RequestMapping("/getName")
    public String getname(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        String realName = user.getRealName();
        return realName;
    }
    @ResponseBody
    @RequestMapping("/getListUser")
    public HashMap<String, Object> ListUser(){
        UserExample example = new UserExample();
        example.setOrderByClause("id ASC");
        List<User> users = userService.find(example);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",users);
        map.put("code",0);
        return map;
    }
}
