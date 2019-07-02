package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_dresses.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DressesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_dresses, container, false)

        var tops = ArrayList<String>()
        tops.add("Evening Gowns")
        tops.add("Cocktail Dresses")
        tops.add("Strapless Dresses")
        tops.add("Sundresses")
        tops.add("Shirt Dresses")



        var adp = ArrayAdapter(activity,R.layout.layout_group,tops)

       v.lv_dresses.adapter = adp

        v.lv_dresses.setOnItemClickListener { parent, view, position, id ->
            AppInfo.type = "Dresses"
            when (position) {
                0 -> {
                    AppInfo.desc = "Evening Gowns"
                    MoveToFragment(ItemsFragment())
                }
                1 -> {
                    AppInfo.desc = "Cocktail Dress"
                    MoveToFragment(ItemsFragment())
                }
                2 -> {
                    AppInfo.desc = "Strapless Dress"
                    MoveToFragment(ItemsFragment())
                }
                3 -> {
                    AppInfo.desc = "Sundress"
                    MoveToFragment(ItemsFragment())
                }
                else -> {
                    AppInfo.desc = "Shirt Dress"
                    MoveToFragment(ItemsFragment())
                }
            }




        }
        return v
    }



    private fun MoveToFragment(frg:Fragment){
        var trans = requireActivity().supportFragmentManager.beginTransaction()
        var obj = frg
        trans.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
        trans.replace(R.id.main_frame,obj)
        AppInfo.act = "closet"
        trans.addToBackStack(null)
        trans.commit()
    }
}
