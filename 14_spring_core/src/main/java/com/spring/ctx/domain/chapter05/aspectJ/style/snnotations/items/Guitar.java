package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items;

import lombok.Getter;
import lombok.Setter;

public class Guitar {

    @Setter
    @Getter
    private String brand;

    public String play(){
        return "G C G C Am D7";
    }

}
