package ru.javawebinar.topjava.repository.jdbc.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeAwareBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {
    private static DateTimeConversionPropertyEditor EDITOR = new DateTimeConversionPropertyEditor();

    public LocalDateTimeAwareBeanPropertyRowMapper(Class<T> mappedClass) {
        super(mappedClass);
    }

    @Override
    protected void initBeanWrapper(BeanWrapper bw) {
        bw.registerCustomEditor(LocalDateTime.class, EDITOR);
    }

    private static class DateTimeConversionPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setValue(Object value) {
            super.setValue(value instanceof Timestamp ? ((Timestamp) value).toLocalDateTime() : value);
        }

        @Override
        public LocalDateTime getValue() {
            return (LocalDateTime)super.getValue();
        }
    }
}
