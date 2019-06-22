package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
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

    /*    var trans = fragmentManager.beginTransaction()
        var obj = ItemsFragment()
        trans.replace(R.id.relative_layout,obj)
        trans.commit()

         v.items_btn.setOnClickListener {
            var trans = fragmentManager.beginTransaction()
            var obj = ItemsFragment()
            trans.replace(R.id.relative_layout,obj)
            trans.commit()
        }
        v.outfits_btn.setOnClickListener {
            var trans = fragmentManager.beginTransaction()
            var obj = OutfitsFragment()
            trans.replace(R.id.relative_layout,obj)
            trans.commit()
        }*/
        return v
    }


}
