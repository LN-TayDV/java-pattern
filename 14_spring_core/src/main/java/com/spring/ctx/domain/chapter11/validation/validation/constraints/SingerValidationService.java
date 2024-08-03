package com.spring.ctx.domain.chapter11.validation.validation.constraints;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service("singerValidationService")
public class SingerValidationService {

    private final Validator validator;

    public SingerValidationService(Validator validator) {
        this.validator = validator;
    }

    public Set<ConstraintViolation<SingerDomain>> validateSinger(SingerDomain singer) {
        return validator.validate(singer);
    }

}
