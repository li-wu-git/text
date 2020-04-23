package com.lw.service;

import com.github.pagehelper.PageInfo;
import com.lw.bean.TAdmin;

import java.util.List;
import java.util.Map; /**
 * @author liwu
 * @create 2020-03-03 15:21
 */
public interface TAdminService {
    TAdmin getTAdminByLogin(Map<String, Object> paramMap);

    PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap);

    void savaTAdmin(TAdmin admin);

    TAdmin selectTAdminById(Integer id);

    void updateTAdmin(TAdmin admin);

    void deleteTAdmin(Integer id);

    void deleteBatch(List<Integer> idsList);
}
