package com.sk.bookz_customer.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sk.bookz_customer.constants.CustomerConstants;
import com.sk.bookz_customer.exception.UtilityException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateFormatUtils extends JsonDeserializer<LocalDate> {


    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String dateString = jsonParser.getText().trim();
        for(DateTimeFormatter formatter: CustomerConstants.FORMATTERS){
            try{
                log.info("dateString:{} and the format: {}",dateString,formatter.toString());
                return LocalDate.parse(dateString, formatter);
            }catch(DateTimeParseException ex){
                log.error("Exception while parsing the date: {}",ex.getMessage());

            }
        }
        throw new UtilityException("Exception while parsing the date");
    }
}
