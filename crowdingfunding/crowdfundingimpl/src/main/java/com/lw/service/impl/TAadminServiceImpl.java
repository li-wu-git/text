package com.lw.service.impl;

import com.github.pagehelper.PageInfo;
import com.lw.bean.TAdmin;
import com.lw.bean.TAdminExample;
import com.lw.exception.LoginException;
import com.lw.mapper.TAdminMapper;
import com.lw.service.TAdminService;
import com.lw.util.AppDateUtils;
import com.lw.util.Const;
import com.lw.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Map;

/**
 * @author liwu
 * @create 2020-03-03 15:22
 */
@Service
public class TAadminServiceImpl implements TAdminService {

    @Autowired
    TAdminMapper adminMapper;

    @Override
    public TAdmin getTAdminByLogin(Map<String, Object> paramMap) {

        //取出数据
        String loginacct = (String) paramMap.get("loginacct");
        String userpswd = (String) paramMap.get("userpswd");

        TAdminExample example = new TAdminExample();
        example.createCriteria().andLoginacctEqualTo(loginacct);
        List<TAdmin> tAdmins = adminMapper.selectByExample(example);

        if (tAdmins == null || tAdmins.size() == 0){
            throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
        }
        TAdmin tAdmin = tAdmins.get(0);
        if (!tAdmin.getUserpswd().equals(MD5Util.digest(userpswd))){
            throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
        }
        return tAdmin ;
    }

    //分页查询用户
    @Override
    public PageInfo<TAdmin> listAdminPage(Map<String, Object> paramMap) {

        String condition = (String) paramMap.get("condition");
        TAdminExample example = new TAdminExample();
        if (!"".equals(condition)){
            example.createCriteria().andLoginacctLike("%"+condition+"%");
            TAdminExample.Criteria criteria1 = example.createCriteria();
            criteria1.andUsernameLike("%"+condition+"%");
            TAdminExample.Criteria criteria2= example.createCriteria();
            criteria2.andEmailLike("%"+condition+"%");
            example.or(criteria1);
            example.or(criteria2);
        }

        List<TAdmin> list = adminMapper.selectByExample(example);

        PageInfo<TAdmin> page = new PageInfo<>(list,5);
        return page;
    }

    //新增用户
    @Override
    public void savaTAdmin(TAdmin admin) {

        admin.setUserpswd(MD5Util.digest(Const.DEFAULT_USERPSWD));

        admin.setCreatetime(AppDateUtils.getFormatTime());

        adminMapper.insertSelective(admin);

    }

    @Override
    public TAdmin selectTAdminById(Integer id) {

        TAdmin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public void updateTAdmin(TAdmin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void deleteTAdmin(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatch(List<Integer> idsList) {

        adminMapper.deleteBatch(idsList);

    }
}
