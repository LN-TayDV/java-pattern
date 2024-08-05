package com.spring.ctx.domain.chapter11.field.formatting;

import com.spring.ctx.domain.chapter11.AppConfig;
import com.spring.ctx.domain.chapter11.PropertyEditors.Blogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class FormattersTest {

    public static void main(String[] args) {

        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class,
            ApplicationConversionServiceFactoryBean.class)) {

            var springBlogger = ctx.getBean("springBlogger", Blogger.class);
            LOGGER.info("SpringBlogger info: {}", springBlogger);

            var awsBlogger = ctx.getBean("awsBlogger", Blogger.class);
            LOGGER.info("AwsBlogger info: {}", awsBlogger);
        }

    }
}
