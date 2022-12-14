package com.example.BD;

import com.twilio.rest.api.v2010.account.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
@Service
public class Sender {

    Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    FileRecipientExtractor fileRecipientExtractor;

    @Autowired
    WishGenerator wishGenerator;

    @Autowired
    TwilioConfiguration twilioConfiguration;

    @Autowired
    TwilioInitializer twilioInitializer;


    @Value("${my.number}")
    private String senderNumber;

    @Value("${my.webhookURL}")
    private String webhookURL;

    public String generateAndPrint() {
        this.logger.info("in generateAndSend");

        try {
            List<Recipient> celebrators = this.fileRecipientExtractor.getCelebratorsList();
            StringBuilder sb = new StringBuilder("");

            for (Recipient r : celebrators) {
                String greeting = this.wishGenerator.generateWish(r);
                sb.append(greeting + ",");
            }

            return sb.toString();
        } catch (Exception var6) {
            return var6.getMessage();
        }
    }


    public void sendWishText(Recipient recipient, String wish) {
        Message.creator(
                        new com.twilio.type.PhoneNumber(recipient.getNumber()),
                        new com.twilio.type.PhoneNumber(twilioConfiguration.getTwilioNumber()),
                        wish)
                .setStatusCallback(URI.create(webhookURL))

                .create();
        System.out.println(wish);
    }

    public void sendSMSStatusUpdate (SmsStatus smsStatus){
        Message.creator(
                        new com.twilio.type.PhoneNumber(senderNumber),
                        new com.twilio.type.PhoneNumber(twilioConfiguration.getTwilioNumber()),
                        "It's " +smsStatus.getTo()+ " Birthday today, your message "+ smsStatus.getMessageStatus()+" to number "+smsStatus.getTo())
                .create();
        System.out.println("sent update");
    }



}
