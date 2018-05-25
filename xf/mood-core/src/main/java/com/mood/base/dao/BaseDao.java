package com.mood.base.dao;

import com.mood.base.Pager;
import com.mood.entity.user.User;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 内容
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface BaseDao<T> {

    public int insert(T t);

    public int update(T t);

    public int deleteById(String id);

    public T selectById(String id);

    public List<T> selectAll(JSONObject param);

    public Pager<T> selectPager(Pager pager);
}
