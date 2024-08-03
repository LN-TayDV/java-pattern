package com.spring.ctx.domain.chapter11.validation.complex;

import com.spring.ctx.domain.chapter11.AppConfig;
import com.spring.ctx.domain.chapter11.FormattingServiceCfg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import java.time.LocalDate;

@Slf4j
public class SpringComplexValidatorTest {

    public static void main(String[] args) {

        try (var ctx = new AnnotationConfigApplicationContext(
            AppConfig.class,
            FormattingServiceCfg.class,
            AddressValidator.class,
            BloggerWithAddressValidator.class
        )) {
            var address = new Address("221B", "UK");

            var blogger = new BloggerWithAddress( null, "Mazzie", LocalDate.of(1973, 1, 1), null, address);

            var bloggerValidator = ctx.getBean(BloggerWithAddressValidator.class);

            var result = new BeanPropertyBindingResult(blogger, "blogger");

            ValidationUtils.invokeValidator(bloggerValidator, blogger, result);

            var errors = result.getAllErrors();

            LOGGER.info(" assertEquals : {}", 2 == errors.size());

            errors.forEach(e -> LOGGER.info("Error Code: {}", e.getCode()));
        }

    }
}
