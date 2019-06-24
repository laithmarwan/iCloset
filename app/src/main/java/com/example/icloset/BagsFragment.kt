package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_bags.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class BagsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_bags, container, false)

        var tops = ArrayList<String>()
        if(AppInfo.Gender == "0"){
            tops.add("Satchels")
            tops.add("Totes")
            tops.add("Clutches")
        }
        else{
            tops.add("Briefcase")
            tops.add("Messenger Bag")
            tops.add("Hobo")
        }


        var adp = ArrayAdapter(activity,R.layout.layout_group,tops)

        v.lv_bags.adapter = adp

    /*    v.BagsListView.setOnItemClickListener { parent, view, position, id ->


        }*/
        return v
    }


}
