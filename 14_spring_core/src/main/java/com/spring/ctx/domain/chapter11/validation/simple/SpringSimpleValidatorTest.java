package com.spring.ctx.domain.chapter11.validation.simple;

import com.spring.ctx.domain.chapter11.AppConfig;
import com.spring.ctx.domain.chapter11.FormattingServiceCfg;
import com.spring.ctx.domain.chapter11.PropertyEditors.Blogger;
import java.net.URL;
import java.time.LocalDate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;

@Slf4j
public class SpringSimpleValidatorTest {

    @SneakyThrows
    public static void main(String[] args) {

        try (var ctx = new AnnotationConfigApplicationContext(
            AppConfig.class,
            FormattingServiceCfg.class,
            SimpleBloggerValidator.class)
        ) {
            var blogger =
                new Blogger("", "Pedala", LocalDate.of(2000, 1, 1), new URL("https://none.co.uk"));

            var bloggerValidator = ctx.getBean(SimpleBloggerValidator.class);

            var result = new BeanPropertyBindingResult(blogger, "blogger");

            ValidationUtils.invokeValidator(bloggerValidator, blogger, result);

            var errors = result.getAllErrors();
            LOGGER.info(" assertEquals : {}", 1 == errors.size());

            errors.forEach(e ->
                LOGGER.info("Object '{}' failed validation. Error code: {}",
                    e.getObjectName(),
                    e.getCode()
                )
            );
        }

    }
}
