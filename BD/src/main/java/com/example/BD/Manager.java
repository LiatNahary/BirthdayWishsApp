package com.example.BD;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

@Service
public class Manager {

    @Autowired
    FileRecipientExtractor fileRecipientExtractor;

    @Autowired
    WishGenerator wishGenerator;

    @Autowired
    Sender sender;

    Logger logger = LoggerFactory.getLogger(Manager.class);


    public void generateAndSendToEachRecipient(){
        logger.info("At generateAndSendToEachRecipient");
        try{
            List<Recipient>  celebrators=  this.fileRecipientExtractor.getCelebratorsList();
            logger.info("There are " + celebrators.size() +" celebrators");
            for (Recipient r : celebrators){
                logger.info("Sending greetings to " + r.getName());
                String greeting = wishGenerator.generateWish(r);
                sender.sendWishText(r,greeting);
            }
            fileRecipientExtractor.clearListOfCelebrators();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}
