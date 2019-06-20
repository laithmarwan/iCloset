package com.example.icloset


import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_closet.*
import kotlinx.android.synthetic.main.fragment_closet.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ClosetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_closet, container, false)

       val recyclerView = v.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false)
        val cats = ArrayList<Categories>()
        var obj = icloset(activity)
        var db = obj.writableDatabase
        var cur= db.rawQuery("select * from item", arrayOf())
        if(cur.count == 0){
            Toast.makeText(activity,"No items",Toast.LENGTH_SHORT).show()

        }
        else{

            cur.moveToNext()
            while (!cur.isAfterLast){
                cats.add(Categories(cur.getString(0)))
                cur.moveToNext()
            }
        }



        val adapter = CustomAdapter(cats)
        recyclerView.adapter = adapter

        v.add_btn.setOnClickListener {
            db.execSQL("insert into item " +
                          "(Type,Description,Weather,Times_worn,Occasion,Available,Item_image)" +
                          " values ('Top','Shirt',1,0,'Party',1,'top_shirt1.png')", arrayOf())
            //db.execSQL("drop table item", arrayOf())
            Toast.makeText(activity,"done",Toast.LENGTH_SHORT).show()

        }

        return v
    }


}
