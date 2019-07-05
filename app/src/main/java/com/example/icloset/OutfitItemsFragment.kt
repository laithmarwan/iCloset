package com.example.icloset


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_outfit_items.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class OutfitItemsFragment : Fragment() {
    lateinit var cats : ArrayList<Outfit>
    lateinit var adapter: OutfitsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_outfit_items, container, false)

        cats = ArrayList()
        val obj = icloset(requireActivity())
        val db = obj.readableDatabase

        val cur = if(AppInfo.desc !="All"){
            db.rawQuery("select * from outfit o,outfit_occasion oo where o.Outfit_ID = oo.Outfit_ID and oo.Occasion = ? and o.Available = 1", arrayOf(AppInfo.desc))
        } else{
            db.rawQuery("select * from outfit where Available = 1", arrayOf())
        }
        if (cur.count == 0) {
            //Toast.makeText(activity,"No items in this category",Toast.LENGTH_SHORT).show()
            v.tv_emptyoutfit.text = "No outfits in this category"
        } else {
            cur.moveToFirst()
            while (!cur.isAfterLast) {


                cats.add(
                    Outfit(
                        cur.getString(cur.getColumnIndex("Outfit_ID")),
                        cur.getString(cur.getColumnIndex("Times_worn")),
                        cur.getString(cur.getColumnIndex("Available")).toInt(),
                        cur.getString(cur.getColumnIndex("Outfit_image"))
                    )
                )


                cur.moveToNext()
            }
        }

        v.rv_outfits.layoutManager = GridLayoutManager(activity, 3)
        adapter = OutfitsAdapter(cats, requireContext())
        v.rv_outfits.adapter = adapter


 return v
}}
