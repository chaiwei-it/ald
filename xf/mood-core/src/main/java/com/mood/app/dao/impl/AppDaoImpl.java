package com.mood.app.dao.impl;

import com.mood.app.dao.AppDao;
import com.mood.app.dao.mapper.AppMapper;
import com.mood.base.Pager;
import com.mood.base.dao.impl.BaseDaoImpl;
import com.mood.entity.app.App;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Repository
public class AppDaoImpl implements AppDao {

   @Autowired
    private AppMapper appMapper;

    @Override
    public int insert(App app) {
        return 0;
    }

    @Override
    public int update(App app) {
        return 0;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public App selectById(String id) {
        return null;
    }

    @Override
    public List<App> selectAll(JSONObject param) {
        return null;
    }

    @Override
    public Pager<App> selectPager(Pager pager) {
        return null;
    }
}
