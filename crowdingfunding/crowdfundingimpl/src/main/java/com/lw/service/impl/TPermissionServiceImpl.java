package com.lw.service.impl;

import com.lw.bean.TPermission;
import com.lw.mapper.TPermissionMapper;
import com.lw.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liwu
 * @create 2020-03-05 16:43
 */

@Service
public class TPermissionServiceImpl implements TPermissionService {

    @Autowired
    TPermissionMapper permissionMapper;


    @Override
    public List<TPermission> selectPermissionAll() {
        return permissionMapper.selectByExample(null);
    }

    @Override
    public void saveTPermission(TPermission permission) {
        permissionMapper.insertSelective(permission);
    }

    @Override
    public TPermission getTPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteTPermission(Integer id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateTPermission(TPermission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }
}
