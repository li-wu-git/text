package com.lw.service;

import com.lw.bean.TMenu;

import java.util.List;

/**
 * @author liwu
 * @create 2020-03-03 21:34
 */
public interface TMenuService {
    List<TMenu> ListMenuAll();

    List<TMenu> listMenuAllTree();

    void saveTMenu(TMenu menu);

    TMenu getTMenuById(Integer id);

    void updateTMenu(TMenu menu);

    void deleteTMenu(Integer id);
}
