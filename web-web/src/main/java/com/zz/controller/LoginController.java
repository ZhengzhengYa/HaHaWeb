package com.zz.controller;

import com.zz.MD5.MD5Utils;
import com.zz.model.User;
import com.zz.model.UserExample;
import com.zz.service.UserService;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @description:
 * @author: ZhangZheng
 * @time: 2020/9/7 15:35
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String loginindex(){
        return "login";
    }

    @RequestMapping("/main")
    public String mainindex(){
        return "main";
    }


    @RequestMapping("/login.json")
    @ResponseBody
    public HashMap<String, Object> login(@RequestBody HashMap<String, String> data, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        String name = data.get("loginUsername");
        String pwd = data.get("loginPassword");
        User user1 = userService.selectName(name);
        String realName = user1.getRealName();
//        System.out.println(realName);
        request.getSession().setAttribute("user",realName);
        request.getSession().setAttribute("loginTime",new Date());
        String s = MD5Utils.md5(pwd);
        User user = userService.selectName(name);
        if (user != null){
            if (user.getPassword().equals(s)){
                map.put("msg", "登陆成功！");
                map.put("code", 0);
            }
        }else{
            map.put("msg", "输入用户名或密码错误！");
            map.put("code",-1);
        }
        return map;
    }

    @RequestMapping("/register.do")
    @ResponseBody
    public HashMap<String, Object> register(@RequestBody HashMap<String, String> data ){
        HashMap<String, Object> map = new HashMap<>();
        String name = data.get("registerUsername");
        String pwd = data.get("registerPassword");
        String realname = data.get("realname");
        String selectValue = data.get("selectValue");
        System.out.println(selectValue);
        String respond = data.get("respond");
        String s = MD5Utils.md5(pwd);
        User user1 = userService.selectName(name);
        if(user1 == null) {
            User user = new User();
            user.setName(name);
            user.setPassword(s);
            user.setRealName(realname);
            user.setSecurityCard(Integer.valueOf(selectValue));
            user.setRespond(respond);
            userService.addUser(user);
            map.put("code",0);
            map.put("msg", "注册成功！");
        }else {
            map.put("code","");
            map.put("msg","用户名已被使用，请重新输入！");
        }
        return map;
    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();//使Session变成无效，及用户退出
        return "login";
    }

    /**
     * 找回密码
     * @param data
     * @return
     */
    @RequestMapping("/forget.do")
    @ResponseBody
    public HashMap<String, Object> forget(@RequestBody HashMap<String, String> data ){
        HashMap<String, Object> map = new HashMap<>();
        String name = data.get("forgetUsername");
        String pwd = data.get("forgetPassword");
        String newPwd = data.get("forgetWellPassword");
        String selectValue = data.get("selectValue");
        String forgetrespond = data.get("forgetrespond");
        String s1 = MD5Utils.md5(pwd);
        String s = MD5Utils.md5(newPwd);
        User user1 = userService.selectName(name);
        String password = user1.getPassword();
        Integer securityCard = user1.getSecurityCard();
        int i = Integer.parseInt(selectValue);
        String respond = user1.getRespond();
        if (!s1.equals(password)) {
                map.put("code", 0);
                map.put("msg", "初始密码输入错误！");
            }
        else if (i != securityCard) {
                map.put("code", 0);
                map.put("msg", "密保问题选择错误!");
            }
        else if (!respond.equals(forgetrespond)) {
                map.put("code", 0);
                map.put("msg", "密保输入错误!");
            }
        else {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andNameEqualTo(name);
            User user = new User();
            user.setId(user1.getId());
            user.setPassword(s);
            userService.updateByExampleSelective(user, userExample);

            map.put("code", 0);
            map.put("msg", "密码修改成功！");
        }
        return map;
    }
}
