package com.mood.app.service.impl;

import com.mood.app.dao.AppDao;
import com.mood.app.service.AppService;
import com.mood.base.service.impl.BaseServiceImpl;
import com.mood.entity.app.App;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class AppServiceImpl extends BaseServiceImpl<App> implements AppService {

    @Autowired
    private AppDao appDao;

    @Override
    public List<App> selectAll(JSONObject param) {
        return appDao.selectAll(param);
    }

    public App selectById(String id){
        return appDao.selectById(id);
    }
}
