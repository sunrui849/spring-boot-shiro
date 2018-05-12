package com.sr.shiro.dao;

import com.sr.shiro.bean.User;
import com.sr.shiro.bean.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/5/12.
 */
@Mapper
public interface DBDealDao {

    public List<User> getAlluser();

    User findByUsername(String username);

    List<UserRole> findRoleByUsername(String username);
}
