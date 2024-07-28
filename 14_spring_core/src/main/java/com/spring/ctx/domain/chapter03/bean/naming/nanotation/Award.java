package com.spring.ctx.domain.chapter03.bean.naming.nanotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Award {
    String[] prize() default {};
}
