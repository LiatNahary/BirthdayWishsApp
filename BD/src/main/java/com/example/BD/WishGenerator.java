package com.example.BD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Objects;

@Service
    public class WishGenerator {

        @Value("${my.signature}")
        private String signature;

        public String generateWish (Recipient recipient){
            if (Objects.equals(recipient.getFormality(), "informal")){
                return "\n"+ recipient.getName()+",\n"
                        +"Didn’t we just celebrate this like a year ago? \n"
                        + "Happy "+ ageOfRecipient(recipient)+ " Birthday, \n" +
                        "Congratulations on another spin around the sun.\n" +
                        "With love "+ signature;
                }
            else  {
           return "\n Dear "+ recipient.getName()+",\n"
                   + "Happy "+ ageOfRecipient(recipient)+ " Birthday, \n" +
                   "I wish you all the joy and happiness on\n" +
                    "your special day and I hope you enjoy it\n" +
                    "surrounded by your nearest and dearest. \n"+
                   " Best regards "+ signature;
            }

        }

        private String ageOfRecipient (Recipient recipient){
            int age = calculateAge(recipient);
            return age + numberOrdinal(age);
        }

        private int calculateAge(Recipient recipient){
            int currentYear= LocalDate.now().getYear();
            LocalDate birthDate = recipient.getBirthDay();
            int yearOfBirth = birthDate.getYear();
            return (currentYear-yearOfBirth);
        }

        private String numberOrdinal (int age){
            int onceUnit= age % 10;
            switch(onceUnit) {
                case 1:
                    return "st";
                case 2:
                    return "nd";
                case 3:
                    return "rd";
                default:
                    return "th";
            }
        }


}
