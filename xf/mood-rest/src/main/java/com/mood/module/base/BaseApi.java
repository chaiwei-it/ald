package com.mood.module.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.common.Mapper;

import javax.servlet.http.HttpServletRequest;

/**
 * 应用模块
 * @author chaiwei
 * @time 2017-11-25 下午08:00
 */
public abstract class BaseApi<T> {

    @Autowired
    private Mapper<T> mapper = null;

    @GetMapping("/{id}")
    public T selectById(@PathVariable(value = "id", required = false) String id,
                        ModelMap modelMap, HttpServletRequest request){
        return mapper.selectByPrimaryKey(id);
    }

    @DeleteMapping("/{id}")
    public Integer deleteById(@PathVariable(value = "id", required = false) String id,
                        ModelMap modelMap, HttpServletRequest request){
        return mapper.deleteByPrimaryKey(id);
    }



}
