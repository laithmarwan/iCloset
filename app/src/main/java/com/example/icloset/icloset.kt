package com.example.icloset

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class icloset(var con:Context) :SQLiteOpenHelper(con,"icloset.db",null,2){
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE outfit_occasion (Outfit_ID INTEGER ,Occasion varchar(10) NOT NULL, PRIMARY KEY (Outfit_ID,Occasion), FOREIGN KEY(Outfit_ID) REFERENCES outfit(Outfit_ID))")

        db.execSQL("CREATE TABLE outfit_weather (Outfit_ID INTEGER ,Weather varchar(10) NOT NULL, PRIMARY KEY (Outfit_ID,Weather), FOREIGN KEY(Outfit_ID) REFERENCES outfit(Outfit_ID))")

        db.execSQL("CREATE TABLE item_occasion (Item_ID INTEGER ,Occasion varchar(10) NOT NULL, PRIMARY KEY (Item_ID,Occasion), FOREIGN KEY(Item_ID) REFERENCES item(Item_ID))")

        db.execSQL("CREATE TABLE item_weather (Item_ID INTEGER ,Weather varchar(10) NOT NULL, PRIMARY KEY (Item_ID,Weather), FOREIGN KEY(Item_ID) REFERENCES item(Item_ID))")

        db.execSQL("CREATE TABLE outfit (" +
                " Outfit_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Weather varchar(10)," +
                " Times_worn integer(10) NOT NULL," +
                " Last_time_worn varchar(20)," +
                " Occasion varchar(20)," +
                " Available integer(1) NOT NULL," +
                " Favorite integer(1) DEFAULT 0," +
                " Outfit_image varchar(30) NOT NULL)")

        db.execSQL("CREATE TABLE item(" +
                " Item_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Type varchar(15) NOT NULL," +
                " Description varchar(20) NOT NULL," +
                " Times_worn integer(20) NOT NULL," +
                " Last_time_worn varchar(20)," +
                " Weather integer(10)," +
                " Occasion varchar(20)," +
                " Available integer(1) NOT NULL," +
                " Item_image varchar(30) NOT NULL" +
                ")")

        db.execSQL("CREATE TABLE color (" +
                " Color_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Color_Name varchar(20),"+
                " Red INTEGER NOT NULL," +
                " Green INTEGER NOT NULL," +
                " Blue INTEGER NOT NULL," +
                " ClassR INTEGER," +
                " ClassG INTEGER," +
                " ClassB INTEGER)")
        db.execSQL("CREATE TABLE complements(" +
                " Color_1 integer(10) NOT NULL ," +
                " Color_2 integer(10) NOT NULL ," +
                " PRIMARY KEY (Color_1,Color_2)," +
                " FOREIGN KEY(Color_1) REFERENCES classes(Class_ID)," +
                " FOREIGN KEY(Color_2) REFERENCES classes(Class_ID))")
        db.execSQL("CREATE TABLE consists_of (" +
                " Outfit_ID integer(10) NOT NULL ," +
                " Item_ID integer(10) NOT NULL ," +
                " PRIMARY KEY (Outfit_ID,Item_ID)," +
                " FOREIGN KEY(Outfit_ID) REFERENCES outfit(Outfit_ID)," +
                " FOREIGN KEY(Item_ID) REFERENCES item(Item_ID))")
        db.execSQL("CREATE TABLE contains(" +
                " Item_ID integer(10) NOT NULL ," +
                " Color_ID integer(10) ," +
                " Class_ID integer(10) ," +
                " Color_percentage integer(11)," +
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

        db.execSQL("CREATE TABLE classes (" +
                " Class_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ClassR integer(10) NOT NULL," +
                " ClassG integer(10) NOT NULL," +
                " ClassB integer(10) NOT NULL)")


        val str = "255\t255\t255\n"+
                "0\t0\t0\n"+
                "0\t0\t25\n"+
                "78\t116\t25\n"+
                "97\t173\t25\n"+
                "122\t211\t25\n"+
                "112\t255\t25\n"+
                "115\t255\t19\n"+
                "116\t255\t14\n"+
                "104\t255\t74\n"+
                "0\t0\t0\n"+        //Double - does not make changes
                "0\t255\t0\n"+
                "203\t255\t47\n"+
                "241\t255\t47\n"+
                "255\t237\t72\n"+
                "255\t249\t13\n"+
                "255\t242\t0\n"+
                "255\t154\t38\n"+
                "255\t188\t54\n"+
                "255\t131\t29\n"+
                "255\t85\t62\n"+
                "255\t109\t93\n"+
                "255\t173\t15\n"+
                "255\t91\t10\n"+
                "255\t0\t0\n"+
                "255\t84\t12\n"+
                "255\t99\t16\n"+
                "255\t88\t18\n"+
                "255\t92\t23\n"+
                "255\t152\t23\n"+
                "255\t1\t18\n"+
                "221\t2\t25\n"+
                "220\t63\t25\n"+
                "244\t123\t25\n"+
                "253\t161\t25\n"+
                "140\t83\t61\n"+
                "143\t93\t47\n"+
                "150\t22\t24\n"+
                "2\t11\t11\n"+
                "107\t34\t57\n"+
                "182\t225\t25\n"+
                "108\t255\t19\n"+
                "248\t255\t17\n"+
                "255\t244\t14\n"+
                "212\t202\t11\n"+
                "51\t94\t18\n"+
                "102\t96\t87\n"+
                "140\t127\t11\n"+
                "184\t191\t17\n"+
                "196\t191\t13\n"+
                "179\t255\t0\n"+
                "78\t15\t15\n"+
                "157\t209\t25\n"+
                "236\t178\t25\n"+
                "232\t213\t14\n"+
                "217\t198\t13\n"+
                "204\t186\t12\n"+
                "53\t116\t20\n"+
                "76\t129\t21\n"+
                "219\t170\t16"



        val arr = str.split("\n")
        for (i in 0 until arr.size) {
            val tokens = arr[i].split("\t")
            db.execSQL("insert into classes (ClassR,ClassG,ClassB) values (?,?,?)", arrayOf(tokens[0],tokens[1],tokens[2]))
        }

        var str2 = "1_1\n1_2\n1_3\n1_4\n1_5\n1_6\n1_7\n1_8\n1_9\n1_10\n1_11\n1_12\n1_13\n1_14\n1_15\n1_16\n1_17\n1_18\n1_19\n1_20\n1_21\n1_22\n1_23\n1_24\n1_25\n1_26\n1_27\n1_28\n1_29\n1_30\n1_31\n1_32\n1_33\n1_34\n1_35\n1_36\n1_37\n1_38\n1_39\n1_40\n1_41\n1_42\n1_43\n1_44\n1_45\n1_46\n1_47\n1_48\n1_49\n1_50\n1_51\n1_52\n1_53\n1_54\n1_55\n1_56\n1_57\n1_58\n1_59\n1_60\n2_1\n2_2\n2_3\n2_4\n2_5\n2_6\n2_7\n2_8\n2_9\n2_10\n2_11\n2_12\n2_13\n2_14\n2_15\n2_16\n2_17\n2_18\n2_19\n2_20\n2_21\n2_22\n2_23\n2_24\n2_25\n2_26\n2_27\n2_28\n2_29\n2_30\n2_31\n2_32\n2_33\n2_34\n2_35\n2_36\n2_37\n2_38\n2_39\n2_40\n2_41\n2_42\n2_43\n2_44\n2_45\n2_46\n2_47\n2_48\n2_49\n2_50\n2_51\n2_52\n2_53\n2_54\n2_55\n2_56\n2_57\n2_58\n2_59\n2_60\n"



        val arr2 = str2.split("\n")
        for (i in 0 until arr2.size) {
            val tokens = arr2[i].split("_")
            db.execSQL("insert into complements (Color_1,Color_2) values (?,?)", arrayOf(tokens[0],tokens[1]))
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}