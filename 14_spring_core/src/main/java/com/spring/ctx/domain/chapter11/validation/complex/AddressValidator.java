package com.spring.ctx.domain.chapter11.validation.complex;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("addressValidator")
public class AddressValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "city", "city.empty");

        ValidationUtils.rejectIfEmpty(errors, "country", "country.empty");

        var address = (Address) target;

        if (!StringUtils.isAlpha(address.city())) {
            errors.rejectValue("city", "city.onlyLettersAllowed");
        }

        if (!StringUtils.isAlpha(address.country())) {
            ValidationUtils.rejectIfEmpty(errors, "country", "country.onlyLettersAllowed");
        }
    }
}
