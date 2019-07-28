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

        val str = "0\t0\t0\n"+
                "0\t0\t255\n" +
                "58\t71\t255\n" +
                "90\t104\t255\n" +
                "119\t137\t255\n" +
                "138\t165\t255\n" +
                "168\t183\t255\n" +
                "199\t207\t255\n" +
                "161\t210\t255\n" +
                "141\t190\t255\n" +
                "120\t183\t255\n" +
                "94\t166\t255\n" +
                "80\t150\t255\n" +
                "56\t146\t255\n" +
                "34\t141\t255\n" +
                "15\t139\t255\n" +
                "29\t168\t255\n" +
                "32\t196\t255\n" +
                "29\t217\t255\n" +
                "38\t251\t255\n" +
                "43\t255\t223\n" +
                "39\t255\t194\n" +
                "33\t255\t155\n" +
                "63\t255\t162\n" +
                "95\t255\t178\n" +
                "118\t255\t200\n" +
                "148\t255\t218\n" +
                "180\t255\t233\n" +
                "142\t255\t244\n" +
                "112\t255\t231\n" +
                "80\t255\t217\n" +
                "80\t255\t217\n" +
                "90\t219\t255\n" +
                "105\t255\t175\n" +
                "145\t255\t178\n" +
                "91\t214\t255\n" +
                "119\t142\t255\n" +
                "153\t168\t255\n" +
                "71\t74\t255\n" +
                "67\t54\t255\n" +
                "119\t77\t255\n" +
                "99\t47\t255\n" +
                "91\t32\t255\n" +
                "154\t123\t255\n" +
                "184\t173\t255\n" +
                "52\t63\t166\n" +
                "62\t118\t171\n" +
                "62\t146\t176\n" +
                "74\t181\t172\n" +
                "90\t186\t153\n" +
                "45\t163\t124\n" +
                "9\t168\t152\n" +
                "167\t206\t212\n" +
                "158\t217\t217\n" +
                "179\t204\t255\n" +
                "39\t45\t99\n" +
                "15\t15\t61\n" +
                "24\t32\t66\n" +
                "36\t45\t71\n" +
                "69\t73\t74\n" +
                "62\t63\t79\n" +
                "22\t84\t69\n" +
                "53\t184\t140\n" +
                "59\t199\t150\n" +
                "59\t250\t167\n" +
                "96\t240\t166\n" +
                "30\t107\t112\n" +
                "127\t95\t255\n" +
                "145\t120\t255\n" +
                "162\t148\t255\n" +
                "181\t168\t255\n" +
                "115\t59\t255\n" +
                "140\t46\t255\n" +
                "120\t10\t255\n" +
                "137\t2\t255\n" +
                "163\t35\t255\n" +
                "177\t74\t255\n" +
                "200\t106\t255\n" +
                "214\t139\t255\n" +
                "217\t155\t255\n" +
                "246\t186\t255\n" +
                "249\t161\t255\n" +
                "241\t136\t255\n" +
                "235\t106\t255\n" +
                "240\t72\t255\n" +
                "238\t50\t255\n" +
                "228\t25\t255\n" +
                "217\t2\t255\n" +
                "255\t1\t200\n" +
                "255\t45\t217\n" +
                "255\t87\t230\n" +
                "255\t127\t219\n" +
                "255\t163\t227\n" +
                "255\t185\t213\n" +
                "255\t157\t198\n" +
                "255\t129\t179\n" +
                "255\t104\t169\n" +
                "255\t91\t162\n" +
                "255\t60\t145\n" +
                "255\t20\t122\n" +
                "255\t0\t140\n" +
                "255\t67\t117\n" +
                "255\t98\t119\n" +
                "255\t141\t158\n" +
                "176\t95\t106\n" +
                "179\t67\t97\n" +
                "184\t49\t99\n" +
                "189\t36\t97\n" +
                "194\t15\t105\n" +
                "163\t11\t84\n" +
                "166\t29\t136\n" +
                "168\t46\t162\n" +
                "81\t31\t135\n" +
                "139\t73\t140\n" +
                "145\t72\t113\n" +
                "120\t57\t122\n" +
                "79\t0\t41\n" +
                "150\t0\t75\n" +
                "110\t0\t44\n" +
                "66\t0\t25\n" +
                "255\t163\t215\n" +
                "255\t217\t230\n" +
                "242\t199\t255\n" +
                "244\t175\t255\n" +
                "255\t176\t209\n" +
                "81\t54\t255\n" +
                "255\t0\t0\n" +
                "255\t64\t64\n" +
                "255\t92\t89\n" +
                "255\t92\t89\n" +
                "255\t92\t89\n" +
                "255\t148\t150\n" +
                "255\t166\t169\n" +
                "255\t106\t123\n" +
                "255\t53\t83\n" +
                "255\t58\t23\n" +
                "255\t80\t45\n" +
                "255\t84\t50\n" +
                "255\t117\t83\n" +
                "255\t168\t128\n" +
                "255\t162\t86\n" +
                "255\t130\t41\n" +
                "255\t157\t29\n" +
                "255\t167\t78\n" +
                "255\t178\t110\n" +
                "255\t194\t140\n" +
                "255\t213\t164\n" +
                "255\t238\t181\n" +
                "255\t250\t157\n" +
                "255\t244\t125\n" +
                "255\t232\t101\n" +
                "255\t219\t76\n" +
                "255\t214\t49\n" +
                "255\t238\t0\n" +
                "248\t255\t50\n" +
                "243\t255\t76\n" +
                "255\t194\t40\n" +
                "255\t178\t36\n" +
                "255\t251\t202\n" +
                "87\t0\t7\n" +
                "112\t0\t32\n" +
                "115\t36\t16\n" +
                "120\t59\t10\n" +
                "125\t91\t27\n" +
                "128\t110\t8\n" +
                "92\t79\t4\n" +
                "88\t94\t17\n" +
                "99\t85\t53\n" +
                "227\t194\t118\n" +
                "242\t229\t195\n" +
                "135\t95\t42\n" +
                "143\t90\t81\n" +
                "153\t46\t43\n" +
                "156\t133\t125\n" +
                "222\t188\t175\n" +
                "235\t199\t185\n" +
                "255\t216\t201\n" +
                "207\t204\t143\n" +
                "255\t244\t226\n" +
                "245\t229\t196\n" +
                "242\t225\t190\n" +
                "235\t219\t184\n" +
                "222\t207\t174\n" +
                "215\t224\t179\n" +
                "245\t255\t204\n" +
                "221\t255\t154\n" +
                "201\t255\t114\n" +
                "0\t255\t0\n" +
                "181\t255\t52\n" +
                "149\t224\t51\n" +
                "85\t227\t19\n" +
                "136\t232\t106\n" +
                "172\t255\t149\n" +
                "197\t255\t178\n" +
                "218\t255\t207\n" +
                "157\t255\t172\n" +
                "96\t255\t144\n" +
                "60\t255\t119\n" +
                "50\t255\t146\n" +
                "50\t223\t147\n" +
                "93\t255\t93\n" +
                "92\t255\t70\n" +
                "144\t255\t84\n" +
                "161\t255\t117\n" +
                "143\t255\t126\n" +
                "147\t255\t154\n" +
                "108\t189\t21\n" +
                "132\t191\t89\n" +
                "204\t255\t0\n" +
                "99\t161\t0\n" +
                "75\t128\t14\n" +
                "88\t130\t65\n" +
                "97\t135\t88\n" +
                "114\t140\t121\n" +
                "75\t143\t83\n" +
                "23\t92\t30\n" +
                "90\t94\t74\n" +
                "99\t96\t92\n" +
                "71\t69\t65\n" +
                "107\t103\t98\n" +
                "140\t135\t128\n" +
                "207\t200\t190\n" +
                "186\t180\t171\n" +
                "153\t148\t140\n" +
                "77\t58\t21\n" +
                "102\t77\t26\n" +
                "112\t84\t29\n" +
                "133\t100\t34\n" +
                "150\t113\t38\n" +
                "184\t138\t47\n" +
                "204\t153\t52\n" +
                "222\t167\t57\n" +
                "235\t177\t60\n" +
                "21\t21\t117\n" +
                "2\t22\t97\n" +
                "14\t95\t102\n" +
                "21\t130\t140\n" +
                "31\t212\t191\n" +
                "35\t217\t181\n" +
                "168\t214\t222\n" +
                "201\t251\t255\n" +
                "188\t235\t237\n" +
                "173\t217\t219\n" +
                "222\t182\t200\n" +
                "196\t158\t172\n" +
                "219\t21\t84\n" +
                "242\t117\t131\n" +
                "229\t123\t245\n" +
                "213\t113\t224\n" +
                "172\t92\t181\n" +
                "145\t77\t153\n" +
                "114\t61\t120\n" +
                "87\t47\t92\n" +
                "105\t48\t74\n" +
                "255\t46\t123\n" +
                "242\t31\t119\n" +
                "247\t234\t118\n" +
                "252\t249\t78\n" +
                "255\t251\t41\n" +
                "255\t247\t135\n" +
                "207\t200\t105\n" +
                "171\t165\t87\n" +
                "255\t160\t76\n" +
                "255\t132\t66\n" +
                "255\t126\t40\n" +
                "232\t112\t32\n" +
                "191\t89\t5\n" +
                "163\t76\t4\n" +
                "166\t73\t7\n" +
                "171\t105\t47\n" +
                "255\t255\t255"

        val arr = str.split("\n")
        for (i in 0 until arr.size) {
            val tokens = arr[i].split("\t")
            db.execSQL("insert into classes (ClassR,ClassG,ClassB) values (?,?,?)", arrayOf(tokens[0],tokens[1],tokens[2]))
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}