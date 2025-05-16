package com.play.playkk.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.util.regex.*;
// detektor linku w stringu
@Component
public class UrlDetector {

    @Value("${url.message}")
    private String url;

    public String detectUrl(String text) {
        Pattern pattern = Pattern.compile(url, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return null;
    }
}
