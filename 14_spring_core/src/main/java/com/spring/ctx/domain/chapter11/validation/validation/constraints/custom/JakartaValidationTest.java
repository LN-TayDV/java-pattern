package com.spring.ctx.domain.chapter11.validation.validation.constraints.custom;

import com.spring.ctx.domain.chapter11.validation.validation.constraints.JakartaValidationCfg;
import com.spring.ctx.domain.chapter11.validation.validation.constraints.SingerDomain;
import com.spring.ctx.domain.chapter11.validation.validation.constraints.SingerValidationService;
import jakarta.validation.ConstraintViolation;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class JakartaValidationTest {

    public static void main(String[] args) {

        testCountrySingerTwoValidation();
        
    }

    static void testCountrySingeValidation() {
        try (var ctx = new AnnotationConfigApplicationContext(JakartaValidationCfg.class)) {

            var singerBeanValidationService = ctx.getBean(SingerValidationService.class);

            SingerDomain singer = new SingerDomain();
            singer.setFirstName("John");
            singer.setLastName("Mayer");
            singer.setGenre(SingerDomain.Genre.COUNTRY);
            singer.setGender(null);

            var violations = singerBeanValidationService.validateSinger(singer);
            LOGGER.info(" assertEquals : {}", 2 == violations.size());

            listViolations(violations);
        }

    }

    static void testCountrySingerTwoValidation() {
        try (var ctx = new AnnotationConfigApplicationContext(JakartaValidationCfg.class)) {
            var singerBeanValidationService = ctx.getBean(SingerTwoValidationService.class);

            var singer = new SingerDomainTwo();
            singer.setFirstName("John");
            singer.setLastName("Mayer");
            singer.setGenre(SingerDomain.Genre.COUNTRY);
            singer.setGender(null);

            var violations = singerBeanValidationService.validateSinger(singer);
            LOGGER.info(" assertEquals : {}", 1 == violations.size());

            violations.forEach(violation ->
                LOGGER.info("Validation error for property: {} with value: {} with error message: {}" ,
                    violation.getPropertyPath(),
                    violation.getInvalidValue(),
                    violation.getMessage()
                )
            );
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
