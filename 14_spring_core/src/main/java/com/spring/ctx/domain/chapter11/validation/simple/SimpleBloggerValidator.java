package com.spring.ctx.domain.chapter11.validation.simple;

import com.spring.ctx.domain.chapter11.PropertyEditors.Blogger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("simpleBloggerValidator")
public class SimpleBloggerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Blogger.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "field.required");
    }
}
