package com.spring.ctx.domain.chapter11.validation.validation.constraints.custom;

import com.spring.ctx.domain.chapter11.validation.validation.constraints.SingerDomain;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CountrySingerValidator
    implements ConstraintValidator<CheckCountrySinger, SingerDomain> {

    @Override
    public void initialize(CheckCountrySinger constraintAnnotation) {
    }

    @Override
    public boolean isValid(SingerDomain singer, ConstraintValidatorContext context) {
        return singer.getGenre() == null
            || (!singer.isCountrySinger()
            || (singer.getLastName() != null && singer.getGender() != null)
        );
    }
}
