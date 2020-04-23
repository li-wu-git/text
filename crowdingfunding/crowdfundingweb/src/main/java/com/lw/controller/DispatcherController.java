package com.lw.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.lw.bean.TAdmin;
import com.lw.bean.TMenu;
import com.lw.service.TAdminService;
import com.lw.service.TMenuService;
import com.lw.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liwu
 * @create 2020-03-03 12:07
 */

@Controller
public class DispatcherController {

    @Autowired
    TMenuService menuService;

    @Autowired
    TAdminService adminService;

    Logger log = LoggerFactory.getLogger(DispatcherServlet.class);



    @RequestMapping("/index")
    public String index(){
        log.debug("用户登录");
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        log.debug("用户注册");
        return "login";
    }

    @RequestMapping("/main")
    public String main(HttpSession session){
        log.debug("用户重定向到main页面");

        List<TMenu> menuList = menuService.ListMenuAll();
        session.setAttribute("menuList",menuList);
        return "main";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){

        log.debug("用户注销");

        if (session != null){

            session.removeAttribute(Const.LOGIN_ADMIN);
            session.invalidate();
        }

        return "redirect:/index";
    }
    @RequestMapping("/doLogin")
    public String doLogin(String loginacct, String userpswd, HttpSession session, Model model){
        log.debug("loginacct={}",loginacct);
        log.debug("userpswd={}",userpswd);

        Map<String,Object> paramMap = new HashMap<>();

        paramMap.put("loginacct",loginacct);
        paramMap.put("userpswd",userpswd);

        try {
            TAdmin admin = adminService.getTAdminByLogin(paramMap);

            session.setAttribute(Const.LOGIN_ADMIN,admin);
            log.debug("登录成功");
            return "redirect:/main";

        } catch (Exception e) {
            e.printStackTrace();

            log.debug("登录失败={}",e.getMessage());
            model.addAttribute("message",e.getMessage());

            return "login";

        }



    }

}
