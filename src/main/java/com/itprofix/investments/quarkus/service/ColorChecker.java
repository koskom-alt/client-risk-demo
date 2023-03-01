package com.itprofix.investments.quarkus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ColorChecker {
    Logger logger = LoggerFactory.getLogger(ColorChecker.class);
    public Boolean checkColor (RequestExample requestExample) {
        logger.info("MESSAGE: {}", requestExample);
        return requestExample.getColor().equals("blue");
    }
}
