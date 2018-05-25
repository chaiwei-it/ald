package com.mood.user.service.impl;

import com.mood.base.Pager;
import com.mood.base.service.impl.BaseServiceImpl;
import com.mood.entity.app.App;
import com.mood.entity.user.User;
import com.mood.user.dao.UserDao;
import com.mood.user.service.UserService;
import com.mood.utils.IdGen;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public int insert(User user) {
        user.setId(IdGen.uuid());
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        return userDao.insert(user);
    }

    @Override
    public int update(User user) {
        user.setUpdateTime(System.currentTimeMillis());
        return userDao.update(user);
    }

    @Override
    public int deleteById(String id) {
        return userDao.deleteById(id);
    }

    @Override
    public User selectById(String id) {
        return userDao.selectById(id);
    }

    @Override
    public List<User> selectAll(JSONObject param) {
        return userDao.selectAll(param);
    }

    @Override
    public Pager<User> selectPager(Pager pager){
        return userDao.selectPager(pager);
    }

    public User selectByUsername(String username){
        JSONObject param = new JSONObject();
        param.put("username", username);
        List<User> userList = userDao.selectAll(param);
        if(userList.size() == 1){
            return userList.get(0);
        }
        return null;
    }

}
