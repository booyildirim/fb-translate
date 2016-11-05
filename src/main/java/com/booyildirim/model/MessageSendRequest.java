package com.booyildirim.model;

import com.booyildirim.artifact.Contact;
import com.booyildirim.artifact.Message;

public class MessageSendRequest {

//    {
//        "recipient": {"id": "1158664320848346"},
//        "message": {"text": "evet ? ne vardi ?"}
//    }

    private Contact recipient;
    private Message message;

    public Contact getRecipient() {
        return recipient;
    }

    public void setRecipient(Contact recipient) {
        this.recipient = recipient;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
