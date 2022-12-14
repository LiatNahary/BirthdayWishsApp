package com.example.BD;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {



    @Value("${my.twilioSID}")
    private  String twilioSID;
    @Value("${my.twilioToken}")
    private  String twilioToken;
    @Value("${my.twilioNumber}")
    private String twilioNumber;

    public TwilioConfiguration (){

    }

    public String getTwilioSID() {
        return twilioSID;
    }

    public void setTwilioSID(String twilioSID) {
        this.twilioSID = twilioSID;
    }

    public String getTwilioToken() {
        return twilioToken;
    }

    public void setTwilioToken(String twilioToken) {
        this.twilioToken = twilioToken;
    }

    public String getTwilioNumber() {
        return twilioNumber;
    }

    public void setTwilioNumber(String twilioNumber) {
        this.twilioNumber = twilioNumber;
    }
}
