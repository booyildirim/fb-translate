package com.booyildirim.artifact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public class FBMessengerApiRequest {

    private String object;
    private MessengerEntry[] entry;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public MessengerEntry[] getEntry() {
        return entry;
    }

    public void setEntry(MessengerEntry[] entry) {
        this.entry = entry;
    }
}
