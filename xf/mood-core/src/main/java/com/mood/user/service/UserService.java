package com.mood.user.service;

import com.mood.base.service.BaseService;
import com.mood.entity.user.User;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface UserService extends BaseService<User> {

    public User selectByUsername(String username);

//    public int insert(User user);
//
//    public int update(User user);
//
//    public int deleteById(String id);
//
//    public User selectById(String id);
//
//    public List<User> selectAll(JSONObject param);

}
