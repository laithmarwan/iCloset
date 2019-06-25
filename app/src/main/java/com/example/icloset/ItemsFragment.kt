package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.android.synthetic.main.fragment_items.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ItemsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_items, container, false)


        Toast.makeText(activity,AppInfo.type + AppInfo.desc,Toast.LENGTH_SHORT).show()
        var arr = ArrayList<Item>()
        arr.add(Item("0","Tops","Blazer",R.id.menu_outfits))


        v.recyclerView.layoutManager = GridLayoutManager(activity,3)
        var myadapter = RecyclerViewAdapter(activity,arr)
        v.recyclerView.adapter = myadapter
        return v
    }


}
