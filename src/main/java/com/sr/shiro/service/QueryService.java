package com.sr.shiro.service;

import com.sr.shiro.bean.User;
import com.sr.shiro.bean.UserRole;
import com.sr.shiro.dao.DBDealDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/5/12.
 */
@Service
public class QueryService {

    @Autowired
    private DBDealDao dbDealDao;

    public List<User> getAlluer(){
        return dbDealDao.getAlluser();
    }

    public User findByUsername(String username) {

        return dbDealDao.findByUsername(username);
    }

    public List<UserRole> findRoleByUsername(String username) {

        return dbDealDao.findRoleByUsername(username);
    }
}
