package com.example.icloset


import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.android.synthetic.main.fragment_items.view.*
import android.provider.MediaStore.Audio.Playlists.Members.moveItem




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ItemsFragment : Fragment() {


   lateinit var cats : ArrayList<Categories>
    lateinit var adapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_items, container, false)
        cats = ArrayList<Categories>()

        //var bmp:Bitmap = BitmapFactory.decodeResource(context?.resources,R.drawable.outfits)
        var obj = icloset(requireActivity())
        var db = obj.readableDatabase
        var cur = db.rawQuery("select * from item where Type=? and Description = ?", arrayOf(AppInfo.type,AppInfo.desc))
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

        v.recyclerView.layoutManager = GridLayoutManager(activity,3)
         adapter = CustomAdapter(cats,requireActivity())
        v.recyclerView.adapter = adapter
        //Toast.makeText(activity,AppInfo.type + AppInfo.desc,Toast.LENGTH_SHORT).show()




        //Drag and Drop
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
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
        itemTouchHelper.attachToRecyclerView(v.recyclerView)

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
