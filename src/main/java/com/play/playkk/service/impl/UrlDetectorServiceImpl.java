package com.play.playkk.service.impl;

import com.play.playkk.service.UrlDetector;
import com.play.playkk.service.UrlDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// detektor linku w sms
@Service
public class UrlDetectorServiceImpl implements UrlDetectorService {

    private final UrlDetector urlDetector;

    @Autowired
    public UrlDetectorServiceImpl(UrlDetector urlDetector) {
        this.urlDetector = urlDetector;
    }

    public String detect(String text) {

       return urlDetector.detectUrl(text);
    }

}
