package com.lambertsoft.contactapp;

import android.net.Uri;

/**
 * Created by InnovaTI on 16-12-14.
 */
public class Contact {
    int id;
    String name, address, phone;
    Uri imageUri;

    public Contact(int _id, String _name, String _address, String _phone, Uri _imageUri) {
        id = _id;
        name = _name;
        address = _address;
        phone = _phone;
        imageUri = _imageUri;
    }

    public int getId() {
        return id;
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

    public Uri getImageUri()
    {
        return imageUri;
    }

}