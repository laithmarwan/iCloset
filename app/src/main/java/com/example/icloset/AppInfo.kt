package com.example.icloset

import android.net.Uri


class AppInfo {

    companion object {

        var web:String="http://tafakkur.com/icloset/"
        var UserID:String=""
        var Name:String=""
        var Email:String=""
        var Address:String=""
        var Gender:String = ""
        var type:String = ""
        var desc:String = ""
        var act:String = ""
        var img_url:Int = 0
        var season:String = ""
        lateinit var img:Uri
        var theme:Int = 0
        var itemID:String = ""
        var day:String = ""
        var month:String = ""
        var year:String = ""
        var occ:String = ""
        lateinit var catarr:ArrayList<Categories>

    }

}