package ru.javawebinar.topjava.repository.jdbc.util;

import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

//supports Java 8 LocalDateTime to Date automatic conversion
public class CombinedSqlParameterSource extends AbstractSqlParameterSource {
    private MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    private BeanPropertySqlParameterSource beanPropertySqlParameterSource;

    public CombinedSqlParameterSource(Object object) {
        this.beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(object);
    }

    public void addValue(String paramName, Object value) {
        mapSqlParameterSource.addValue(paramName, value);
    }

    @Override
    public boolean hasValue(String paramName) {
        return beanPropertySqlParameterSource.hasValue(paramName) || mapSqlParameterSource.hasValue(paramName);
    }

    @Override
    public Object getValue(String paramName) {
        Object result =  beanPropertySqlParameterSource.hasValue(paramName) ?
                beanPropertySqlParameterSource.getValue(paramName) :
                mapSqlParameterSource.getValue(paramName);

        if (result instanceof LocalDateTime) {
            return Date.from(((LocalDateTime) result).atZone(ZoneId.systemDefault()).toInstant());
        } else {
            return result;
        }
    }

    @Override
    public int getSqlType(String paramName) {
        return  beanPropertySqlParameterSource.hasValue(paramName) ?
                beanPropertySqlParameterSource.getSqlType(paramName) :
                mapSqlParameterSource.getSqlType(paramName);
    }

    @Override
    public void registerSqlType(String paramName, int sqlType) {
        mapSqlParameterSource.registerSqlType(paramName, sqlType);
        beanPropertySqlParameterSource.registerSqlType(paramName, sqlType);
    }
}