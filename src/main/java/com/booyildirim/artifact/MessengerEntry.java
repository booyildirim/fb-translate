package com.booyildirim.artifact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = false)
public class MessengerEntry {
    private String id;
    private Date time;
    private Messaging[] messaging;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Messaging[] getMessaging() {
        return messaging;
    }

    public void setMessaging(Messaging[] messaging) {
        this.messaging = messaging;
    }
}
