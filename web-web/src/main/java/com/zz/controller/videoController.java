package com.zz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: ZhangZheng
 * @time: 2020/10/9 15:03
 */
@Controller
public class videoController {

    @RequestMapping("/video")
    public String video(){
        return "video";
    }
}
