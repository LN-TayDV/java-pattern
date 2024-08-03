package com.spring.ctx.domain.chapter11.validation.complex;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("bloggerWithAddressValidator")
public class BloggerWithAddressValidator implements Validator {

    private final Validator addressValidator;

    public BloggerWithAddressValidator(Validator addressValidator) {
        if (!addressValidator.supports(Address.class)) {
            throw new IllegalArgumentException("The supplied [Validator] must support the validation of [Address] instances.");
        }
        this.addressValidator = addressValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BloggerWithAddress.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalSite", "personalSite.required");

        var b = (BloggerWithAddress) target;

        if (StringUtils.isEmpty(b.getFirstName()) && StringUtils.isEmpty(b.getLastName())) {
            errors.rejectValue("firstName", "firstNameOrLastName.required");
            errors.rejectValue("lastName", "firstNameOrLastName.required");
        }

        try {
            errors.pushNestedPath("address");
            ValidationUtils.invokeValidator(this.addressValidator, b.getAddress(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}
