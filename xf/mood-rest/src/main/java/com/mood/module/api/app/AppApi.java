package com.mood.module.api.app;


import com.mood.entity.app.App;
import com.mood.module.base.BaseApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用模块
 * @author chaiwei
 * @time 2017-11-25 下午08:00
 */
@RestController
@RequestMapping("/api/{version}")
public class AppApi extends BaseApi<App> {

    @GetMapping("test")
    public String getApps(){
        return "连接成功";
    }

}
