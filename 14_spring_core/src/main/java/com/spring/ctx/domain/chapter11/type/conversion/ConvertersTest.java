package com.spring.ctx.domain.chapter11.type.conversion;

import com.spring.ctx.domain.chapter11.AppConfig;
import com.spring.ctx.domain.chapter11.PropertyEditors.Blogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.ConversionService;

@Slf4j
public class ConvertersTest {

    public static void main(String[] args) {

        try (
            var ctx = new AnnotationConfigApplicationContext(AppConfig.class, ConverterCfg.class)) {

            var springBlogger = ctx.getBean("springBlogger", Blogger.class);
            LOGGER.info("SpringBlogger info: {}", springBlogger);

            var conversionService = ctx.getBean(ConversionService.class);
            var simpleBlogger = conversionService.convert(springBlogger, SimpleBlogger.class);
            LOGGER.info("simpleBlogger info: {}", simpleBlogger);
        }

    }
}
