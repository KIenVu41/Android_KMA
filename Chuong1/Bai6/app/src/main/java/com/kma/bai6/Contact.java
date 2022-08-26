package com.kma.bai6;

import java.util.ArrayList;

public class Contact {
    private String id;
    private String name;
    private ArrayList<String> phoneNo;

    public Contact() {
    }

    public Contact(String id, String name, ArrayList<String> phoneNo) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(ArrayList<String> phoneNo) {
        this.phoneNo = phoneNo;
    }
}
