package com.lw.service;

import com.lw.bean.TPermission;
import com.lw.mapper.TPermissionMapper;

import java.util.List;

/**
 * @author liwu
 * @create 2020-03-05 16:42
 */
public interface TPermissionService {


    List<TPermission> selectPermissionAll();

    void saveTPermission(TPermission permission);

    TPermission getTPermissionById(Integer id);

    void deleteTPermission(Integer id);

    void updateTPermission(TPermission permission);
}
