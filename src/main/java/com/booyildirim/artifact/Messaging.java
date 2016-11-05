package com.booyildirim.artifact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Messaging {

    private Contact sender;
    private Contact recipient;
    private Date timestamp;
    private MessageInfo message;

    public Contact getSender() {
        return sender;
    }

    public void setSender(Contact sender) {
        this.sender = sender;
    }

    public Contact getRecipient() {
        return recipient;
    }

    public void setRecipient(Contact recipient) {
        this.recipient = recipient;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public MessageInfo getMessage() {
        return message;
    }

    public void setMessage(MessageInfo message) {
        this.message = message;
    }
}

