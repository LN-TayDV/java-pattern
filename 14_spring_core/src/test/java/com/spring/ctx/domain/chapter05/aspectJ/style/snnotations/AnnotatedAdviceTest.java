/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.items.Guitar;
import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.items.NewDocumentarist;
import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.items.PretentiosGuitarist;
import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.v1.AfterAdviceV1;
import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.v1.AspectJAopConfigV1;
import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.v1.BeforeAdviceV1;
import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.v2.AfterThrowingAdviceV2;
import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.v2.AspectJAopConfigV2;
import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.v2.BeforeAdviceV2;
import java.util.Arrays;
import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.v2.CommandingDocumentarist;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class AnnotatedAdviceTest {

    @Test
    void testBeforeAdviceV1() {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfigV1.class, BeforeAdviceV1.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("beforeAdviceV1"));
        NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();
        ctx.close();
    }

    @Test
    void testAfterAdviceV1(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfigV1.class, AfterAdviceV1.class);
        ctx.refresh();

        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("afterAdviceV1"));
        var guitar = new Guitar();
        var guitarist = ctx.getBean("agustin", PretentiosGuitarist.class);

        //guitarist.sing(guitar);
        LOGGER.info("-------------------");
        guitar.setBrand("Musicman");
        assertThrows(IllegalArgumentException.class, () -> guitarist.sing(guitar), "Unacceptable guitar!");
        ctx.close();
    }

    @Test
    void testBeforeAdviceV2() {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfigV2.class, BeforeAdviceV2.class);
        ctx.refresh();
        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("beforeAdviceV2"));
        NewDocumentarist documentarist =
            ctx.getBean("commandingDocumentarist", CommandingDocumentarist.class);
        documentarist.execute();
        ctx.close();
    }

    @Test
    void testAfterThrowingAdviceV2(){
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectJAopConfigV2.class, AfterThrowingAdviceV2.class);
        ctx.refresh();

        assertTrue(Arrays.asList(ctx.getBeanDefinitionNames()).contains("afterThrowingAdviceV2"));

        var guitar = new Guitar();
        var guitarist = ctx.getBean("Martin", PretentiosGuitarist.class);
        guitarist.sing(guitar);

        LOGGER.info("-------------------");
        guitar.setBrand("Musican");
        assertThrows(RuntimeException.class, () -> guitarist.sing(guitar), "Unacceptable guitar!");
        ctx.close();
    }
}
