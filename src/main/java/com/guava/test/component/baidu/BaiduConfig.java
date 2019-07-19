package com.guava.test.component.baidu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaiduConfig {

    @Value("${baidu.server.apis}")
    private String apis;
    @Value("${baidu.server.name}")
    private String serverName;

    public String getApis() {
        return apis;
    }

    public void setApis(String apis) {
        this.apis = apis;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
