package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations;

import com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.before.BeforeAdviceV1;
import com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.NewDocumentarist;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnnotatedAdviceTest {

    @Test
    void testBeforeAdviceV1(){
        var ctx = new AnnotationConfigApplicationContext();ctx.register(AspectJAopConfig.class, BeforeAdviceV1.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("beforeAdviceV1"));
        NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();
        ctx.close();
    }
}
