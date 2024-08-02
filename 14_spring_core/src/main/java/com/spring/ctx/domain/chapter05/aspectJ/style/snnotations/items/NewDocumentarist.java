package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items;

import com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.GrammyGuitarist;
import com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("documentarist")
public class NewDocumentarist {

    protected GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");
        guitarist.sing(guitar);
        guitarist.talk();
    }

    @Autowired
    @Qualifier("johnMayer")
    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }

}
