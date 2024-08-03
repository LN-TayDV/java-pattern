package com.spring.ctx.domain.chapter11.validation.validation.constraints.custom;

import com.spring.ctx.domain.chapter11.validation.validation.constraints.SingerDomain;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service("singerTwoValidationService")
public class SingerTwoValidationService {

    private final Validator validator;

    public SingerTwoValidationService(Validator validator) {
        this.validator = validator;
    }

    public Set<ConstraintViolation<SingerDomainTwo>> validateSinger(SingerDomainTwo singer) {
        return validator.validate(singer);
    }
}
