package com.example.icloset


import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


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
        cats.add(Categories("Tops"))
        cats.add(Categories("Tops"))
        cats.add(Categories("Tops"))
        cats.add(Categories("Tops"))
        cats.add(Categories("Tops"))

        val adapter = CustomAdapter(cats)
        recyclerView.adapter = adapter


        return v
    }


}
