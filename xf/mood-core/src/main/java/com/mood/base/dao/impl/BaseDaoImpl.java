package com.mood.base.dao.impl;

import com.mood.base.dao.BaseDao;
import com.mood.entity.user.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 内容
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    @Autowired
    private Mapper<T> mapper = null;

    @Override
    public int insert(T t) {
        return mapper.insert(t);
    }

    @Override
    public int update(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public T selectById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectAll(JSONObject param) {
        return mapper.selectByExample(param);
    }

}
