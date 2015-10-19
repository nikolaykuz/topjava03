package ru.javawebinar.topjava.util.converter;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 15.04.2015.
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    private DateTimeFormatter formatter;

    @Required
    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LocalDateTime convert(String param) {
        return TimeUtil.toDateTime(param, formatter);
    }
}
