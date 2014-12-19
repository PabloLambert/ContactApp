package com.lambertsoft.contactapp;

/**
 * Created by InnovaTI on 16-12-14.
 */
public class Contact {
    String name, address, phone;

    public Contact(String _name, String _address, String _phone) {
        name = _name;
        address = _address;
        phone = _phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return  address;
    }

    public String getPhone() {
        return phone;
    }

}