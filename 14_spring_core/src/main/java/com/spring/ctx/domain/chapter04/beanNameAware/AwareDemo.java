package com.spring.ctx.domain.chapter04.beanNameAware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class AwareDemo {

    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext(AwareConfig.class);
        ctx.registerShutdownHook();

//        var singer = ctx.getBean(NamedSinger.class);
//        singer.sing();

        // Lấy tất cả các bean của kiểu NamedSinger
        Map<String, NamedSinger> singers = ctx.getBeansOfType(NamedSinger.class);

        // Lặp qua danh sách bean
        for (Map.Entry<String, NamedSinger> entry : singers.entrySet()) {
            NamedSinger singer = entry.getValue();
            singer.sing();
        }
    }
}
