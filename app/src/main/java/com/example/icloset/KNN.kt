package com.example.icloset

import android.content.Context
import kotlin.math.pow
import kotlin.math.sqrt

class KNN(con:Context) {
    var graph = Array(256) { Array(256) { IntArray(256) } }

    init {

        val obj = icloset(con)
        val db = obj.readableDatabase
        val cur = db.rawQuery("select ClassR,ClassG,ClassB from classes", arrayOf())
        if(cur.count!=0){
            cur.moveToFirst()
            while(!cur.isAfterLast){
                graph[cur.getInt(0)][cur.getInt(1)][cur.getInt(2)] = 1

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
                    if(graph[i][j][k] == 1){
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