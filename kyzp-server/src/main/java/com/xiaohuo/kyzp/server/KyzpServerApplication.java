package com.xiaohuo.kyzp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${kyzp.info.base-package}
@SpringBootApplication(scanBasePackages = {"${kyzp.info.base-package}.server", "${kyzp.info.base-package}.module"})
public class KyzpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KyzpServerApplication.class, args);
//        new SpringApplicationBuilder(KyzpServerApplication.class)
//                .applicationStartup(new BufferingApplicationStartup(20480))
//                .run(args);
    }

}
