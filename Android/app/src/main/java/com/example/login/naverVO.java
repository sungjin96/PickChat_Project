package com.example.login;

import java.util.Arrays;

public class naverVO {
String type;
String contentType;
String countryCode;
String from;
String[] to;
String subject;
String content;

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", contentType='" + contentType + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", from='" + from + '\'' +
                ", to=" + Arrays.toString(to) +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
