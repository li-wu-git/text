package com.lw.service.impl;

import com.lw.bean.TMenu;
import com.lw.mapper.TMenuMapper;
import com.lw.service.TMenuService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liwu
 * @create 2020-03-03 21:35
 */

@Service
public class TMenuServiceImpl implements TMenuService {

    Logger log = LoggerFactory.getLogger(TMenuServiceImpl.class);

    @Autowired
    TMenuMapper menuMapper;

    @Override
    public List<TMenu> ListMenuAll() {

        //父亲菜单
        List<TMenu> menuList = new ArrayList<TMenu>();
        Map<Integer,TMenu> cache = new HashMap<Integer, TMenu>();
        //所有的菜单
        List<TMenu> allMenus = menuMapper.selectByExample(null);

        for (TMenu menu : allMenus) {
            if (menu.getPid() == 0){
                menuList.add(menu);
                cache.put(menu.getId(),menu);
            }
        }

        for (TMenu tMenu : allMenus) {
            if (tMenu.getPid() != 0){
                TMenu parent = cache.get(tMenu.getPid());
                parent.getChildren().add(tMenu);

            }
        }
        log.debug("菜单：{}",menuList);
        return menuList;
    }

    @Override
    public List<TMenu> listMenuAllTree() {
        return menuMapper.selectByExample(null);
    }

    @Override
    public void saveTMenu(TMenu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public TMenu getTMenuById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateTMenu(TMenu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void deleteTMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
