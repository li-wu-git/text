package com.lw.service.impl;

import com.github.pagehelper.PageInfo;
import com.lw.bean.TRole;
import com.lw.bean.TRoleExample;
import com.lw.bean.TRolePermissionExample;
import com.lw.mapper.TAdminRoleMapper;
import com.lw.mapper.TRoleMapper;
import com.lw.mapper.TRolePermissionMapper;
import com.lw.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author liwu
 * @create 2020-03-04 19:26
 */
@Service
public class TRoleServiceImpl implements TRoleService {

    @Autowired
    TRoleMapper roleMapper;

    @Autowired
    TAdminRoleMapper adminRoleMapper;

    @Autowired
    TRolePermissionMapper rolePermissionMapper;

    @Override
    public PageInfo<TRole> selectTRolePage(Map<String, Object> paramMap) {

        String condition = (String) paramMap.get("condition");

        List<TRole> list = null;

        if (StringUtils.isEmpty(condition)){
            list = roleMapper.selectByExample(null);
        }else {
            TRoleExample example = new TRoleExample();
            example.createCriteria().andNameLike("%"+condition+"%");
            list = roleMapper.selectByExample(example);
        }

        PageInfo<TRole> page = new PageInfo<>(list,5);

        return page;
    }

    @Override
    public void saveTRole(TRole role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public TRole getTRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateTRole(TRole role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteTRoleById(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TRole> listAllTRole() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public List<Integer> getRoleIdByAdminId(String id) {
        return adminRoleMapper.getRoleIdByAdminId(id);
    }

    @Override
    public void saveAdminAndRoleRealitonShip(Integer[] roleId, Integer adminId) {
        adminRoleMapper.saveAdminAndRoleRealitonShip(roleId,adminId);
    }

    @Override
    public void deleteAdminAndRoleRealitonShip(Integer[] roleId, Integer adminId) {
        adminRoleMapper.deleteAdminAndRoleRealitonShip(roleId,adminId);
    }

    @Override
    public void saveRoleAndPermissionRelationship(Integer roleId, List<Integer> ids) {

        //先删除原先的数据
        TRolePermissionExample example = new TRolePermissionExample();

        example.createCriteria().andRoleidEqualTo(roleId);

        rolePermissionMapper.deleteByExample(example);

        rolePermissionMapper.saveRoleAndPermissionRelationship(roleId,ids);
    }

    @Override
    public List<Integer> listPermissionIdByRoleId(Integer roleId) {
        return rolePermissionMapper.listPermissionIdByRoleId(roleId);
    }


}
