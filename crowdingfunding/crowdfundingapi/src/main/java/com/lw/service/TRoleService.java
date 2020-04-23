package com.lw.service;

import com.github.pagehelper.PageInfo;
import com.lw.bean.TRole;

import java.util.List;
import java.util.Map; /**
 * @author liwu
 * @create 2020-03-04 19:26
 */
public interface TRoleService {

    PageInfo<TRole> selectTRolePage(Map<String, Object> paramMap);

    void saveTRole(TRole role);

    TRole getTRoleById(Integer id);

    void updateTRole(TRole role);

    void deleteTRoleById(Integer id);

    List<TRole> listAllTRole();


    List<Integer> getRoleIdByAdminId(String id);

    void saveAdminAndRoleRealitonShip(Integer[] roleId, Integer adminId);

    void deleteAdminAndRoleRealitonShip(Integer[] roleId, Integer adminId);

    void saveRoleAndPermissionRelationship(Integer roleId, List<Integer> ids);

    List<Integer> listPermissionIdByRoleId(Integer roleId);
}
