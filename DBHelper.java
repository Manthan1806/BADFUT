package com.example.himanshu.trial1;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Trial.db";
    public static final String TABLE_NAME = "registered_users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_PASSWORD = "pwd";
    //public static final String COLUMN_CPASSWORD = "cpwd";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exists registered_users(email text,phone text,pwd text)"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS registered_users");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public boolean insertEntry(String email, String phone, String pwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email",email);
        values.put("phone",phone);
        values.put("pwd",pwd);
        db.insert("registered_users",null,values);
        //db.execSQL("Insert into registered_users values(email,phone,pwd,cpwd)");
        return true;
    }

    public String getData()
    {
        String dbString = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from registered_users";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        do {
            if(c.getString(c.getColumnIndex(COLUMN_EMAIL))!=null)
            {
                dbString += c.getString(c.getColumnIndex(COLUMN_EMAIL));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_PHONE));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex(COLUMN_PASSWORD));
                dbString += " ";
                dbString +="\n";
            }

        }while(c.moveToNext());
        return dbString;
    }
}
