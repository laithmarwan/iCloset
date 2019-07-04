package com.example.icloset


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.cardview_item.view.*
import kotlinx.android.synthetic.main.fragment_calendar_to_outfits.view.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CalendarToOutfits : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_calendar_to_outfits, container, false)

        var cats = ArrayList<Outfit>()

        var obj = icloset(requireActivity())
        var db = obj.readableDatabase
        var cur = db.rawQuery("select * from outfit", arrayOf())
        if(cur.count ==0){
            v.tv_emptycal.text = "No outfits"
        }
        else{
            cur.moveToFirst()
            while (!cur.isAfterLast){


                cats.add(Outfit(cur.getString(cur.getColumnIndex("Outfit_ID")),
                    cur.getString(cur.getColumnIndex("Times_worn")),
                    cur.getString(cur.getColumnIndex("Available")).toInt(),
                    cur.getString(cur.getColumnIndex("Outfit_image"))))


                cur.moveToNext()
            }
        }


        v.rv_calendar.layoutManager = GridLayoutManager(activity, 3)
        val adapter = CalendarAdapter(cats)
        v.rv_calendar.adapter = adapter



        return v
    }


}
