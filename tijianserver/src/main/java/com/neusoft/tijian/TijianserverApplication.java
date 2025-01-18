package com.neusoft.tijian;

import com.fasterxml.jackson.core.io.IOContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TijianserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(TijianserverApplication.class, args);
    }

    // IoC 控制反转：小时候，吃饭的权限在老妈手上，长大了之后，吃饭的权限，反转回来自己手上
    // 创建组件的权限，反转交给 Spring 了，原来 new User，现在不需要了
    // 区别：在底层，自动实现

    // DI 依赖注入：护士打针，你哪里疼，就打哪里
    // service 需要 dao，所以要在 service 注入 dao 的实例
    // 区别：你需要的时候，再手动实现
}
