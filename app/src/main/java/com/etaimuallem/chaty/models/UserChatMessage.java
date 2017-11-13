package com.etaimuallem.chaty.models;

/**
 * Created by etaimuallem on 12/07/2017.
 */

public class UserChatMessage {
    private String userName;
    private String message;
    private String userId;
    private String messageTime;

    public UserChatMessage() {
    }

    public UserChatMessage(String userName, String message, String userId, String messageTime) {

        this.userName = userName;
        this.message = message;
        this.userId = userId;
        this.messageTime = messageTime;
    }

    public String getMessageTime() {

        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
