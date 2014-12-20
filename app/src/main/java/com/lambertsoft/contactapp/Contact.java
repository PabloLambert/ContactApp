package com.lambertsoft.contactapp;

import android.net.Uri;

/**
 * Created by InnovaTI on 16-12-14.
 */
public class Contact {
    String name, address, phone;
    Uri imageUri;

    public Contact(String _name, String _address, String _phone, Uri _imageUri) {
        name = _name;
        address = _address;
        phone = _phone;
        imageUri = _imageUri;
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