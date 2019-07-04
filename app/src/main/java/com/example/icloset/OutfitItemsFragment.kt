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
        var v = inflater.inflate(R.layout.fragment_outfit_items, container, false)

        cats = ArrayList()
        var obj = icloset(requireActivity())
        var db = obj.readableDatabase
        var cur = db.rawQuery("select * from outfit where Available = 1", arrayOf())
        if(cur.count ==0){
            //Toast.makeText(activity,"No items in this category",Toast.LENGTH_SHORT).show()
            v.tv_emptyoutfit.text = "No outfits in this category"
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

        v.rv_outfits.layoutManager = GridLayoutManager(activity,3)
        adapter = OutfitsAdapter(cats,requireContext())
        v.rv_outfits.adapter = adapter
        //Toast.makeText(activity,AppInfo.type + AppInfo.desc,Toast.LENGTH_SHORT).show()




        //Drag and Drop
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.END,
            ItemTouchHelper.RIGHT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                moveItem(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteItem(viewHolder.adapterPosition)
            }

        })
        itemTouchHelper.attachToRecyclerView(v.rv_outfits)

        return v
    }

    fun moveItem(oldPos: Int, newPos: Int) {

        val item = cats.get(oldPos)
        cats.removeAt(oldPos)
        cats.add(newPos, item)
        adapter.notifyItemMoved(oldPos, newPos)
    }

    fun deleteItem(position: Int) {

        cats.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

}
