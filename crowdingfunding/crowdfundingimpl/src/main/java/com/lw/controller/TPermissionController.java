package com.lw.controller;

import com.lw.bean.TPermission;
import com.lw.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liwu
 * @create 2020-03-05 16:31
 */
@Controller
public class TPermissionController {




    @Autowired
    TPermissionService permissionService;

    @ResponseBody
    @RequestMapping("/permission/edit")
    public String updateTPermission(TPermission permission){
        permissionService.updateTPermission(permission);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/permission/delete")
    public String deleteTPermission(Integer id){
        permissionService.deleteTPermission(id);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/permission/get")
    public TPermission addTPermission(Integer id){

        return permissionService.getTPermissionById(id);
    }

    @ResponseBody
    @RequestMapping("/permission/add")
    public String addTPermission(TPermission permission){
        permissionService.saveTPermission(permission);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/permission/loadTree")
    public List<TPermission> loadTree(){
        return permissionService.selectPermissionAll();
    }


    @RequestMapping("/permission/index")
    public String index(){

        return "permission/index";
    }
}
