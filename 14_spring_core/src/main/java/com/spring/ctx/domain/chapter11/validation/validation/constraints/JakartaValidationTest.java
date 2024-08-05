package com.spring.ctx.domain.chapter11.validation.validation.constraints;

import jakarta.validation.ConstraintViolation;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class JakartaValidationTest {

    public static void main(String[] args) {

        try (var ctx = new AnnotationConfigApplicationContext(JakartaValidationCfg.class)) {

            var singerBeanValidationService = ctx.getBean(SingerValidationService.class);

            SingerDomain singer = new SingerDomain();
            singer.setFirstName("J");
            singer.setLastName("Mayer");
            singer.setGenre(null);
            singer.setGender(null);

            var violations = singerBeanValidationService.validateSinger(singer);

            LOGGER.info(" assertEquals : {}", 2 == violations.size());

            listViolations(violations);
        }

    }

    private static void listViolations(Set<ConstraintViolation<SingerDomain>> violations) {
        violations.forEach(violation ->
            LOGGER.info(
                "Validation error for property: {} with value: {} with error message: {}",
                violation.getPropertyPath(),
                violation.getInvalidValue(),
                violation.getMessage()
            )
        );
    }
}
