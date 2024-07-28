package com.spring.ctx.domain.chapter03.bean.naming;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @Component:
 *
 * @Component là một annotation chung để khai báo một class là một bean.
 * Các subclass của @Component như @Service, @Repository, và @Controller
 * cung cấp ý nghĩa ngữ cảnh cụ thể hơn và có thể được sử dụng để phân biệt các lớp
 * khác nhau dựa trên chức năng của chúng trong ứng dụng.
 *
 * @Configuration:
 *
 * @Configuration là một loại đặc biệt của @Component
 * được sử dụng để khai báo một class là một class cấu hình (configuration class).
 * Các class này thường chứa các phương thức với @Bean để định nghĩa và cấu hình các bean khác.
 */
@Slf4j
public class BeanNamingDemo {

    public static void main(String... args) {

        var ctx = new AnnotationConfigApplicationContext(BeanNamingCfg.class);

        //Arrays.stream(ctx.getBeanDefinitionNames()).forEach(beanName -> log.info(beanName));

        var simpleBeans = ctx.getBeansOfType(SimpleBean.class);
        simpleBeans.forEach((k,v) -> {
            var aliases = ctx.getAliases(k);
            if(aliases.length > 0) {
                log.info("Aliases for {} ", k);
                Arrays.stream(aliases).forEach(a -> log.info("\t {}", a));
            }
        });
    }
}
