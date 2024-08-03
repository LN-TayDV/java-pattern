package com.spring.ctx.domain.chapter11;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class FormattingServiceCfg {

    @Bean
    public FormattingConversionService conversionService() {

        var formattingConversionServiceBean = new DefaultFormattingConversionService(true);

        formattingConversionServiceBean.addFormatter(localDateFormatter());

        return formattingConversionServiceBean;
    }

    protected Formatter<LocalDate> localDateFormatter() {

        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String source, Locale locale) throws ParseException {
                return LocalDate.parse(source, getDateTimeFormatter());
            }

            @Override
            public String print(LocalDate source, Locale locale) {
                return source.format(getDateTimeFormatter());
            }

            protected DateTimeFormatter getDateTimeFormatter() {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd");
            }
        };
    }
}
