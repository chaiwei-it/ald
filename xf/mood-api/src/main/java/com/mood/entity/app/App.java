package com.mood.entity.app;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Data
@Table(name = "mood_app")
public class App extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String code;

//    private String appKey;

}