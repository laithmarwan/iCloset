package com.example.icloset

import android.content.Context
import kotlin.math.pow
import kotlin.math.sqrt

class KNN(con:Context) {
    var graphR = IntArray(256)
    var graphG = IntArray(256)
    var graphB = IntArray(256)


    init {

        val obj = icloset(con)
        val db = obj.readableDatabase
        val cur = db.rawQuery("select ClassR,ClassG,ClassB from classes", arrayOf())
        if(cur.count!=0){
            cur.moveToFirst()
            while(!cur.isAfterLast){
                graphR[cur.getInt(0)] = 1
                graphG[cur.getInt(1)] = 1
                graphB[cur.getInt(2)] = 1
                cur.moveToNext()
            }
        }

    }

    fun classify(x:Double,y:Double,z:Double): String {
        var min =  99999.0
        var mini = 0
        var minj = 0
        var mink = 0

        for (i in 0..255)
        {
            for (j in 0..255)
            {
                for (k in 0..255)
                {
                    if(graphR[i] == 1 && graphG[j]==1 && graphB[k] == 1){
                        val d = sqrt((x-i).pow(2)+(y-j).pow(2)+(z-k).pow(2))
                        if( d < min){
                            min = d
                            mini = i
                            minj = j
                            mink = k
                        }
                    }
                }
            }
        }

        return "$mini/$minj/$mink"
    }





}