package com.mood.entity.base;

import lombok.Data;

/**
 * 基础实体
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Data
public class BaseEntity{

        /** 创建时间-数据库字段 */
        private Long createTime;
        /** 修改时间-数据库字段  */
        private Long updateTime;
        /** 创建人  */
        private String createPerson;
        /** 修改人   */
        private String updatePerson;
        /** 创建时间－页面字段  */
        private Integer delStatus;

}
