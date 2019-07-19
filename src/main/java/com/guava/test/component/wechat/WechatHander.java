package com.guava.test.component.wechat;

import com.sun.net.httpserver.HttpServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WechatHander {

    public String accept(Map param){
        HttpServer httpServer=WeChartHttpBulider.bulidPost(param);
        httpServer.getAddress();//
        //....
        return "";
    }
}
