package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_accessories.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AccessoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_accessories, container, false)

        var tops = ArrayList<String>()

        tops.add("Watches")
        tops.add("Sunglasses")
        tops.add("Belts")
        tops.add("Hats")
        tops.add("Necklaces")
        tops.add("Bracelets")
        tops.add("Rings")
        tops.add("Headbands")
        tops.add("Earrings")
        tops.add("Scarves")

        var adp = ArrayAdapter(activity,R.layout.layout_group,tops)

        v.lv_accessories.adapter = adp

       /* v.AccsListView.setOnItemClickListener { parent, view, position, id ->


        }*/
        return v
    }


}
