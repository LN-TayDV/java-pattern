package com.spring.ctx.domain.chapter11.validation.complex;

import com.spring.ctx.domain.chapter11.PropertyEditors.Blogger;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ComplexBloggerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Blogger.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        var b = (Blogger) target;

        if (StringUtils.isEmpty(b.getFirstName()) && StringUtils.isEmpty(b.getLastName())) {

            errors.rejectValue("firstName", "firstNameOrLastName.required");

            errors.rejectValue("lastName", "firstNameOrLastName.required");
        }

        if (b.getBirthDate().isBefore(LocalDate.of(1983, 1, 1))) {

            errors.rejectValue("birthDate", "birthDate.greaterThan1983");
        }
    }
}
