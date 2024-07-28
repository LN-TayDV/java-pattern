package com.spring.ctx.domain.chapter03.bean.naming;

import com.spring.ctx.domain.chapter03.bean.naming.styles.SimpleBeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Đoạn này giải thích về cách đặt tên bean trong Spring khi sử dụng @Configuration.
 * Khi bạn sử dụng @Configuration để đánh dấu một class là một class cấu hình (configuration class) trong Spring,
 * class này tự động được đánh dấu là một bean bằng cách sử dụng @Component.
 * Do đó, bất kỳ class cấu hình nào cũng chính là một định nghĩa bean.
 *
 * Cụ thể, tên của bean này sẽ mặc định là tên đơn giản của class cấu hình đó
 * (simple name of the configuration class).
 * Ví dụ, nếu bạn có một class cấu hình tên là BeanNamingCfg, thì tên của bean này sẽ là "beanNamingCfg".
 *
 * Điều này có nghĩa là bạn có thể sử dụng tên bean "beanNamingCfg"
 * để tham chiếu đến bean đó trong các phần khác của ứng dụng Spring của bạn.
 */

@Configuration// cũng là bean
@ComponentScan(
        nameGenerator = SimpleBeanNameGenerator.class,
        basePackages = "com.spring.ctx.domain.chapter03.bean.naming"
)
public class BeanNamingCfg {

    /**
     * As you can see, an entry named anotherSimpleBean is listed,
     * which means that a SimpleBean was created and named as the method that created it.
     */
    @Bean("simpleBeanTwo")
    public SimpleBean anotherSimpleBean(){
        return new SimpleBean();
    }

    @Bean({"simpleBeanThree", "three", "numero_tres"})
    public SimpleBean simpleBean3(){
        return new SimpleBean();
    }
}
