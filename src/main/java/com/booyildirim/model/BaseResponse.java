package com.booyildirim.model;

public class BaseResponse {
    private boolean success;
    private String appMessage;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAppMessage() {
        return appMessage;
    }

    public void setAppMessage(String appMessage) {
        this.appMessage = appMessage;
    }
}
