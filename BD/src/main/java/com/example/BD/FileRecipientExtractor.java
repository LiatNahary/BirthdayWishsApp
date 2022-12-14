package com.example.BD;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FileRecipientExtractor {

    @Value("${my.region}")
    private String region;

    @Value("${my.buketName}")
    private String buket_name;

    @Value("${my.key}")
    private String key;

    @Value("${my.recipientFile}")
    private File file;


    Logger logger = LoggerFactory.getLogger(FileRecipientExtractor.class);

    List<Recipient> celebrateToday = new ArrayList<>();



    private List<Recipient> extractRecipientsList2(){
        logger.info("At extractRecipientsList with file of " + file);
        return Poiji.fromExcel( file,Recipient.class);
    }



    private List<Recipient> extractRecipientsList() throws Exception{
      this.logger.info("in extractRecipientsList");
        AmazonS3 s3 = (AmazonS3)((AmazonS3ClientBuilder)AmazonS3ClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1)).build();
        try {
            S3Object o = s3.getObject(buket_name, key);
            logger.info("S3Object:" + o);
            S3ObjectInputStream s3is = o.getObjectContent();
            logger.info("s3is:" + s3is);
            List<Recipient> recipients = Poiji.fromExcel (s3is, PoijiExcelType.XLSX, Recipient.class);
            s3is.close();
            return recipients;
        } catch (Exception var5) {
            this.logger.error(var5.getMessage());
            throw var5;
        }
    }

    public List<Recipient> getCelebratorsList () throws Exception{
        List<Recipient> recipients = this.extractRecipientsList();
        return this.filterCelebrators(recipients);
    }

    private List<Recipient> filterCelebrators(List<Recipient> recipients) {

        for (Recipient recipient : recipients) {
            if (this.isBirthdayToday(recipient)) {
                this.celebrateToday.add(recipient);
            }
        }

        return this.celebrateToday;
    }

    private boolean isBirthdayToday(Recipient recipient){
        int nowDay = LocalDate.now().getDayOfMonth();
        Month nowMonth = LocalDate.now().getMonth();
        int recipientDay = recipient.getBirthDay().getDayOfMonth();
        Month recipientMonth= recipient.getBirthDay().getMonth();
        return (nowDay == recipientDay && nowMonth == recipientMonth);
    }

    public void clearListOfCelebrators (){
        celebrateToday.clear();
    }



}
