package com.example.icloset

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class icloset(var con:Context) :SQLiteOpenHelper(con,"icloset.db",null,2){
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE outfit (" +
                " Outfit_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Weather varchar(10) NOT NULL," +
                " Times_worn integer(10) NOT NULL," +
                " Last_time_worn varchar(20) NOT NULL," +
                " Occasion varchar(20) NOT NULL," +
                " Available integer(1) NOT NULL" +
                ")")

        db.execSQL("CREATE TABLE item(" +
                " Item_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Type varchar(15) NOT NULL," +
                " Description varchar(20) NOT NULL," +
                " Times_worn integer(20) NOT NULL," +
                " Last_time_worn varchar(20)," +
                " Weather integer(10) NOT NULL," +
                " Occasion varchar(20) NOT NULL," +
                " Available integer(1) NOT NULL," +
                " Item_image varchar(30) NOT NULL" +
                ")")

        db.execSQL("CREATE TABLE color (" +
                " Color_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Color_Name varchar(20) NOT NULL"+
                ")")
        db.execSQL("CREATE TABLE complements(" +
                " Color_1 integer(10) NOT NULL ," +
                " Color_2 integer(10) NOT NULL ," +
                " PRIMARY KEY (Color_1,Color_2)," +
                " FOREIGN KEY(Color_1) REFERENCES color(Color_ID)," +
                " FOREIGN KEY(Color_2) REFERENCES color(Color_ID))")
        db.execSQL("CREATE TABLE consists_of (" +
                " Outfit_ID integer(10) NOT NULL ," +
                " Item_ID integer(10) NOT NULL ," +
                " PRIMARY KEY (Outfit_ID,Item_ID)," +
                " FOREIGN KEY(Outfit_ID) REFERENCES outfit(Outfit_ID)," +
                " FOREIGN KEY(Item_ID) REFERENCES item(Item_ID))")
        db.execSQL("CREATE TABLE contains(" +
                " Item_ID integer(10) NOT NULL ," +
                " Color_ID integer(10) ," +
                " Color_percentage integer(11) NOT NULL," +
                " PRIMARY KEY (Item_ID,Color_ID)," +
                " FOREIGN KEY(Item_ID) REFERENCES item(Item_ID)," +
                " FOREIGN KEY(Color_ID) REFERENCES color(Color_ID))")

        db.execSQL("CREATE TABLE matches (" +
                " Item_1 integer(10) NOT NULL ," +
                " Item_2 integer(10) NOT NULL ," +
                " PRIMARY KEY (Item_1,Item_2)," +
                " FOREIGN KEY(Item_1) REFERENCES item(Item_ID)," +
                " FOREIGN KEY(Item_2) REFERENCES item(Item_ID))")

        db.execSQL("CREATE TABLE reminders (" +
                " Reminder_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Date varchar(20) NOT NULL," +
                " Outfit_ID integer(10) NOT NULL ," +
                " FOREIGN KEY(Outfit_ID) REFERENCES outfit(Outfit_ID))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}