package com.example.icloset


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_new_outfit.view.*


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
    //    v.add_btn.setOnClickListener {
            var cats = ArrayList<Categories>()
            var obj = icloset(requireActivity())
            var db = obj.readableDatabase
            var cur = db.rawQuery("select * from item where Type=? and Description = ?", arrayOf("Tops","Shirts"))
            if(cur.count ==0){
                Toast.makeText(activity,"No items in this category", Toast.LENGTH_SHORT).show()
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

            v.rv_add.layoutManager = GridLayoutManager(activity,1)
            val adapter = DraggableAdapter(cats)
            v.rv_add.adapter = adapter
    //    }

        return v
    }


}
