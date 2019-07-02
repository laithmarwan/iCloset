package com.example.icloset
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_new_outfit.*
import kotlinx.android.synthetic.main.fragment_new_outfit.view.*
import java.io.File


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewOutfitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var v = inflater.inflate(R.layout.fragment_new_outfit, container, false)

        var cats = ArrayList<Categories>()
        var obj = icloset(requireActivity())
        var db = obj.readableDatabase
        var cur = db.rawQuery("select * from item where Type=?", arrayOf(AppInfo.type))
        if(cur.count ==0){
            Toast.makeText(activity,"No items in this category",Toast.LENGTH_SHORT).show()
        }
        else{
            cur.moveToFirst()
            while (!cur.isAfterLast){


                cats.add(Categories(cur.getString(cur.getColumnIndex("Item_ID")),
                    cur.getString(cur.getColumnIndex("Type")),
                    cur.getString(cur.getColumnIndex("Description")),
                    cur.getString(cur.getColumnIndex("Item_image"))))


                cur.moveToNext()
            }
        }

        v.recView.layoutManager = GridLayoutManager(activity,3)
        val adapter = CustomAdapter(cats, requireActivity())
        v.recView.adapter = adapter
        //Toast.makeText(activity,AppInfo.type + AppInfo.desc,Toast.LENGTH_SHORT).show()






        return v
    }

}
