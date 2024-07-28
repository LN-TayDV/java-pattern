package com.spring.ctx.domain.chapter03.parameters.injection.spEL;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component("injectSimpleConfig")
@Getter
@Setter
class InjectSimpleConfig {

    private String name = "John Mayer";

    private int age = 40;

    private float height = 1.92f;

    private boolean developer = false;

    private Long ageInSeconds = 1_241_401_112L;

}
