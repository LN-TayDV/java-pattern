package com.spring.ctx.domain.chapter11.PropertyEditors;

import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomEditorCfg {

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        var cus = new CustomEditorConfigurer();

        cus.setCustomEditors(
            Map.of(LocalDate.class, LocalDatePropertyEditor.class)
        );

        return cus;
    }
}
