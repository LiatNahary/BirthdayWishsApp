package com.example.BD;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TwilioInitializer {

    Logger logger = LoggerFactory.getLogger(TwilioInitializer.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration){
        this.twilioConfiguration=twilioConfiguration;
        Twilio.init(twilioConfiguration.getTwilioSID(),twilioConfiguration.getTwilioToken());
        logger.info("twilio initialized");
    }
//
//    @Autowired
//    private TwilioConfiguration twilioConfiguration;
//
//    @PostConstruct
//    public void initializeTwilio (){
//        Twilio.init(twilioConfiguration.getTwilioSID(),twilioConfiguration.getTwilioToken());
//        logger.info("twilio initialized with sid" +  twilioConfiguration.getTwilioSID() + " and token of " +twilioConfiguration.getTwilioToken());
//    }


}
