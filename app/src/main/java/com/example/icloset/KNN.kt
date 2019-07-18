package com.example.icloset

import kotlin.math.pow
import kotlin.math.sqrt

class KNN {
    var graph = arrayOf<Array<Array<Int>>>()


    init {
        graph[0][0][0] = 1
    }

    fun classify(x:Double,y:Double,z:Double): String {
        var min = 0.0
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