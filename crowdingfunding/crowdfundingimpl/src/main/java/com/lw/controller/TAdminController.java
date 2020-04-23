package com.lw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lw.bean.TAdmin;
import com.lw.bean.TRole;
import com.lw.service.TAdminService;
import com.lw.service.TRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liwu
 * @create 2020-03-03 22:41
 */
@Controller
public class TAdminController {

    @Autowired
    TAdminService adminService;

    @Autowired
    TRoleService roleService;

    Logger log = LoggerFactory.getLogger(TAdminController.class);

    @ResponseBody
    @RequestMapping("/admin/doUnAssign")
    public String doUnAssign(Integer[] roleId,Integer adminId){
        roleService.deleteAdminAndRoleRealitonShip(roleId,adminId);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/admin/doAssign")
    public String doAssign(Integer[] roleId,Integer adminId){
        roleService.saveAdminAndRoleRealitonShip(roleId,adminId);
        log.debug("adminid:{}",adminId);
        for (Integer integer : roleId) {
            log.debug("roleid:{}",integer);
        }
        return "ok";
    }

    //给用户分配角色
    @RequestMapping("/admin/toAssign")
    public String toAssign(String id,Model model){

        //1.查询全部的角色
        List<TRole> roleList = roleService.listAllTRole();

        //2.根据id查询以分配的角色
        List<Integer> idList = roleService.getRoleIdByAdminId(id);

        //3.筛选出未分配的角色
        List<TRole> assignRole = new ArrayList<>();
        List<TRole> unassignRole = new ArrayList<>();

        for (TRole tRole : roleList) {
            if (idList.contains(tRole.getId())){
                assignRole.add(tRole);
            }else {
                unassignRole.add(tRole);
            }
        }

        //4.筛选出已分配的角色

        model.addAttribute("assignRole",assignRole);
        model.addAttribute("unassignRole",unassignRole);






        return "admin/assignRole";
    }


    @RequestMapping("/admin/doDeleteBatch")
    public String doDeleteBatch(String ids,Integer pageNum){

        List<Integer> idsList = new ArrayList<Integer>();

        String[] split = ids.split(",");

        for (String s : split) {

            int i = Integer.parseInt(s);
            idsList.add(i);


        }

        adminService.deleteBatch(idsList);

        return "redirect:/admin/index?pageNum="+pageNum;
    }

    //删除用户
    @RequestMapping("/admin/doDelete")
    public String doDelete(Integer id,Integer pageNum){

        adminService.deleteTAdmin(id);

        return "redirect:/admin/index?pageNum="+pageNum;
    }

    @RequestMapping("/admin/doUpdate")
    public String doUpdate(TAdmin admin,Integer pageNum){

        adminService.updateTAdmin(admin);

        return "redirect:/admin/index?pageNum="+pageNum;
    }

    @RequestMapping("/admin/toUpdate")
    public String toUpdate(Integer id,Model model){

        TAdmin admin = adminService.selectTAdminById(id);
        model.addAttribute("admin",admin);
        return "admin/update";
    }

    @RequestMapping("/admin/doAdd")
    public String doAdd(TAdmin admin){

        adminService.savaTAdmin(admin);

        return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;
    }

    @RequestMapping("/admin/toAdd")
    public String toAdd(){

        return "admin/add";
    }

    //用户查询
    @RequestMapping("/admin/index")
    public String index(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize",required = false,defaultValue = "2") Integer pageSize,
                        Model model,
                        @RequestParam(value = "condition",required = false,defaultValue = "") String condition ){

        Map<String,Object> paramMap = new HashMap<>();

        paramMap.put("condition",condition);

        PageHelper.startPage(pageNum,pageSize);
        PageInfo<TAdmin> page = adminService.listAdminPage(paramMap);

        model.addAttribute("page",page);

        return "admin/index";
    }



}
