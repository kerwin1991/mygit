package com.wxp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by Administrator on 2018/6/5.
 */
@SpringBootApplication
@EnableConfigServer
public class Config_3344_APP {

    public static void main(String[] args) {
        // 127.0.0.1 config-3344.com
        SpringApplication.run(Config_3344_APP.class, args);
    }

    /**
     * 以下路径，可以访问到远程的yml文件内容， application-***  代表不同环境的配置
     * http://config-3344.com:3344/application-dev.yml
     * http://config-3344.com:3344/application-test.yml
     * 没有配置的环境，只能后的公共的yml配置内容
     * http://config-3344.com:3344/application-abc.yml
     */
}
