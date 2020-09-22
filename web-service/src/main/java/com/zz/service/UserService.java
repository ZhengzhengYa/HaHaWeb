package com.zz.service;

import com.zz.model.User;
import com.zz.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: ZhangZheng
 * @time: 2020/9/7 15:04
 */
public interface UserService {
    boolean searchUser(String name, String password);
    List<User> find(UserExample example);
    int addUser(User user);
    User selectName(String name);

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
