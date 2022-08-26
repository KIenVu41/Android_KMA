package com.kma.bai6;

public class Message {
    private String address;
    private String message;

    public Message(String address, String message) {
        this.address = address;
        this.message = message;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "address='" + address + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
