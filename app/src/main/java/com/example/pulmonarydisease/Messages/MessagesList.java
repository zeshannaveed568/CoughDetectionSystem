package com.example.pulmonarydisease.Messages;

public class MessagesList {

    private String message, name, lastMessage;

    private  int unseenMessages;

    public MessagesList(String message, String name, String lastMessage, int unseenMessages) {
        this.message = message;
        this.name = name;
        this.lastMessage = lastMessage;
        this.unseenMessages = unseenMessages;
    }


    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getUnseenMessages() {
        return unseenMessages;
    }
}
