
package com.example.BD;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.context.annotation.Configuration;


import java.util.Objects;


public class SmsStatus
{

    @JsonProperty("SmsStatus")
    private String smsStatus;
    @JsonProperty("MessageStatus")
    private String messageStatus;
    @JsonProperty("To")
    private String to;


    public SmsStatus() {
    }


    public String getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmsStatus smsStatus1 = (SmsStatus) o;
        return Objects.equals(smsStatus, smsStatus1.smsStatus) && Objects.equals(messageStatus, smsStatus1.messageStatus) && Objects.equals(to, smsStatus1.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smsStatus, messageStatus, to);
    }

    @Override
    public String toString() {
        return "SmsStatus{" +
                " smsStatus='" + smsStatus + '\'' +
                ", messageStatus='" + messageStatus + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
