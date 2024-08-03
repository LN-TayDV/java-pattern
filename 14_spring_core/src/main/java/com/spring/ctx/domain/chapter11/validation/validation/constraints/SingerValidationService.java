package com.spring.ctx.domain.chapter11.validation.validation.constraints;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import java.util.Set;

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
