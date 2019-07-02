package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_closet.*
import kotlinx.android.synthetic.main.fragment_tops.*
import kotlinx.android.synthetic.main.fragment_tops.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TopsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_tops, container, false)
        var tops = ArrayList<String>()
        tops.add("Blazers")
        tops.add("Shirts")
        tops.add("Sweaters")
        tops.add("T-Shirts")
        tops.add("Sleeveless")

        var adp = ArrayAdapter(activity,R.layout.layout_group,tops)

        v.ListView.adapter = adp

        v.ListView.setOnItemClickListener { parent, view, position, id ->

            AppInfo.type = "Tops"
            when (position) {
                0 -> {
                    AppInfo.desc = "Blazers"
                    MoveToFragment(ItemsFragment())
                }
                1 -> {
                    AppInfo.desc = "Shirts"
                    MoveToFragment(ItemsFragment())
                }
                2 -> {
                    AppInfo.desc = "Sweaters"
                    MoveToFragment(ItemsFragment())
                }
                3 -> {
                    AppInfo.desc = "T-shirts"
                    MoveToFragment(ItemsFragment())
                }
                else -> {
                    AppInfo.desc = "Sleeveless"
                    MoveToFragment(ItemsFragment())
                }
            }



        }
        return v
    }



    private fun MoveToFragment(frg:Fragment){
        requireActivity().supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
            replace(R.id.main_frame , frg)
            addToBackStack(null)
            AppInfo.act = "closet"
            commit()
        }
    }
}
