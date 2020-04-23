package com.lw.controller;

import com.lw.bean.TMenu;
import com.lw.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author liwu
 * @create 2020-03-05 12:38
 */
@Controller
public class TMenuContorller {

    @Autowired
    TMenuService menuService;




    @ResponseBody
    @RequestMapping("/menu/doDelete")
    public String doDelete(Integer id){

        menuService.deleteTMenu(id);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/menu/doUpdate")
    public String doUpdate(TMenu menu){

        menuService.updateTMenu(menu);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/menu/getTMenuById")
    public TMenu getTMenuById(Integer id){

        return menuService.getTMenuById(id);
    }

    @ResponseBody
    @RequestMapping("/menu/doAdd")
    public String doAdd(TMenu menu){

        menuService.saveTMenu(menu);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/menu/loadTree")
    public List<TMenu> loadTree(){

        return menuService.listMenuAllTree();
    }

    @RequestMapping("/menu/index")
    public String index(){

        return "menu/index";
    }
}
