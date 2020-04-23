package com.lw.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lw.bean.TRole;
import com.lw.service.TRoleService;
import com.lw.util.Datas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liwu
 * @create 2020-03-04 19:25
 */
@Controller
public class TRoleController {

    @Autowired
    TRoleService roleService;



    //回显已分配的权限
    @ResponseBody
    @RequestMapping("/role/listPermissionIdByRoleId")
    public List<Integer>  listPermissionIdByRoleId(Integer roleId){

        List<Integer> idList = roleService.listPermissionIdByRoleId(roleId);

        return idList;
    }

    @ResponseBody
    @RequestMapping("/role/doAssignPermissionToRole")
    public String doAssignPermissionToRole(Integer roleId, Datas datas){

        roleService.saveRoleAndPermissionRelationship(roleId,datas.getIds());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/role/doDelete")
    public String doDelete(Integer id){

        roleService.deleteTRoleById(id);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/role/doUpdate")
    public String doUpdate(TRole role){

        roleService.updateTRole(role);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/role/getTRoleById")
    public TRole getTRoleById(Integer id){
        return roleService.getTRoleById(id);
    }

    @ResponseBody
    @RequestMapping("/role/doAdd")
    public String add(TRole role){

        roleService.saveTRole(role);
        return "ok";
    }

    @RequestMapping("/role/index")
    public String index(){

        return "role/index";
    }


    @ResponseBody
    @RequestMapping("/role/loadDate")
    public PageInfo<TRole> loadDate(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "condition",required = false,defaultValue = "") String condition){

        PageHelper.startPage(pageNum,pageSize);

        Map<String,Object> paramMap = new HashMap<>();

        paramMap.put("condition",condition);

        PageInfo<TRole> page = roleService.selectTRolePage(paramMap);
        return  page;
    }


}
