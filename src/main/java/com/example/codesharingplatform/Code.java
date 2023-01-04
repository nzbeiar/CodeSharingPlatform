package com.example.codesharingplatform;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
public class Code {

    String code;
    String date;

    private static final String DATE_FORMATTER= "yyyy/MM/dd HH:mm:ss";

    @JsonCreator
    public Code(@JsonProperty("code") String code) {
        this.code = code;
        this.date = formatDate(LocalDateTime.now());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = formatDate(date);
    }

    private String formatDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return localDateTime.format(formatter);
    }
}
