package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
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

        v.lv_accessories.setOnItemClickListener { parent, view, position, id ->
            AppInfo.type = "accessories"
            when (position) {
                0 -> {
                    AppInfo.desc = "watches"
                    MoveToFragment(ItemsFragment())
                }
                1 -> {
                    AppInfo.desc = "sunglasses"
                    MoveToFragment(ItemsFragment())
                }
                2 -> {
                    AppInfo.desc = "belts"
                    MoveToFragment(ItemsFragment())
                }
                3 -> {
                    AppInfo.desc = "hats"
                    MoveToFragment(ItemsFragment())
                }
                4 -> {
                    AppInfo.desc = "necklaces"
                    MoveToFragment(ItemsFragment())
                }
                5 -> {
                    AppInfo.desc = "bracelets"
                    MoveToFragment(ItemsFragment())
                }
                6 -> {
                    AppInfo.desc = "rings"
                    MoveToFragment(ItemsFragment())
                }
                7 -> {
                    AppInfo.desc = "headbands"
                    MoveToFragment(ItemsFragment())
                }
                8 -> {
                    AppInfo.desc = "earrings"
                    MoveToFragment(ItemsFragment())
                }
                else -> {
                    AppInfo.desc = "scarves"
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
        trans.addToBackStack(null)
        trans.commit()
    }

}
