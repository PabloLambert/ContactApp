package com.lambertsoft.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.internal.app.ToolbarActionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by InnovaTI on 20-12-14.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "contactmanager",
    TABLE_CONTACT = "contacts",
    KEY_ID = "id",
    KEY_NAME = "name",
    KEY_ADDRESS = "address",
    KEY_PHONE = "phone",
    KEY_IMAGEURI = "imageUri";

    public DataBaseHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACT + " (" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_NAME +" TEXT, "+ KEY_ADDRESS +" TEXT, "+ KEY_PHONE +" TEXT, "+ KEY_IMAGEURI +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_CONTACT);
        onCreate(db);
    }

    public void createContact( Contact cc) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, cc.getName());
        values.put(KEY_ADDRESS, cc.getAddress());
        values.put(KEY_PHONE, cc.getPhone());
        values.put(KEY_IMAGEURI, cc.getImageUri().toString());

        db.insert(TABLE_CONTACT, null, values);
        db.close();

    }

    public Contact getContact(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACT, new String[]{KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_PHONE, KEY_IMAGEURI}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Contact cc = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Uri.parse(cursor.getString(4)));
        cursor.close();
        db.close();
        return cc;
    }

    public void deleteContact(Contact cc) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CONTACT, KEY_ID + "=?", new String[]{String.valueOf(cc.getId())});
        db.close();
    }

    public int getContactsCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_CONTACT, null);
        int i = cursor.getCount();
        db.close();
        cursor.close();

        return i;
    }

    public int updateContact( Contact cc ) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, cc.getName());
        values.put(KEY_ADDRESS, cc.getAddress());
        values.put(KEY_PHONE, cc.getPhone());
        values.put(KEY_IMAGEURI, cc.getImageUri().toString());

        int res =  db.update(TABLE_CONTACT, values, KEY_ID + "=?", new String[] { String.valueOf(cc.getId())});
        db.close();

        return res;

    }

    public List<Contact> getAllContacts() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Contact> contactList = new ArrayList<Contact>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Contact cc = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Uri.parse(cursor.getString(4)));
                contactList.add(cc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }
}
