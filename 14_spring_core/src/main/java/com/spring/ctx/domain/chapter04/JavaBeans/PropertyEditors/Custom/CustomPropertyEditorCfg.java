package com.spring.ctx.domain.chapter04.JavaBeans.PropertyEditors.Custom;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.spring.ctx.domain.chapter04.JavaBeans.PropertyEditors.Custom")
public class CustomPropertyEditorCfg {
    @Bean
    CustomEditorConfigurer customEditorConfigurer(){

        var cust = new CustomEditorConfigurer();

        cust.setCustomEditors(Map.of(FullName.class, NamePropertyEditor.class));

        return cust;
    }

}
