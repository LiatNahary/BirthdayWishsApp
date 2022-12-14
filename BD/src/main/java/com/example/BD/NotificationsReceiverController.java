package com.example.BD;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationsReceiverController {

    Logger logger = LoggerFactory.getLogger("NotificationsReceiverController");

    @Autowired
    Sender sender;

    @PostMapping(path = "/twilioSendStatus", consumes = "application/json")
    public void getStatus(@RequestBody SmsStatus status){


        if(status.getMessageStatus().equalsIgnoreCase("delivered")){
            logger.info("Got delivered message. YAY!");
            sender.sendSMSStatusUpdate(status);
        }
        if (status.getMessageStatus().equalsIgnoreCase("failed")){
            logger.info("Got failed message. NO!");
            sender.sendSMSStatusUpdate(status);

        }
        else {
            logger.info("Got message of " + status.getMessageStatus());
        }


    }

    @GetMapping("/twilioSendStatus")
    public void getStatus(){
        logger.info("Got delivered message. YAY!");
    }




}
