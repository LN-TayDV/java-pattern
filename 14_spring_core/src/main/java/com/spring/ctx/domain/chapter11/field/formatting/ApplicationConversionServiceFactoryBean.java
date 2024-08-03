package com.spring.ctx.domain.chapter11.field.formatting;

import jakarta.annotation.PostConstruct;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Service;

@Service("conversionService")
@Slf4j
public class ApplicationConversionServiceFactoryBean extends
    FormattingConversionServiceFactoryBean {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private final Set<Formatter<?>> formatters = new HashSet<>();
    private DateTimeFormatter dateTimeFormatter;
    private String datePattern = DEFAULT_DATE_PATTERN;

    public String getDatePattern() {
        return datePattern;
    }

    @Autowired(required = false)
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @PostConstruct
    public void init() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
        formatters.add(getDateTimeFormatter());
        setFormatters(formatters);
    }

    public Formatter<LocalDate> getDateTimeFormatter() {

        return new Formatter<>() {
            @Override
            public LocalDate parse(String source, Locale locale) throws ParseException {
                LOGGER.info("Parsing date string: " + source);
                return LocalDate.parse(source, dateTimeFormatter);
            }

            @Override
            public String print(LocalDate source, Locale locale) {
                LOGGER.info("Formatting datetime: " + source);
                return source.format(dateTimeFormatter);
            }
        };
    }
}
