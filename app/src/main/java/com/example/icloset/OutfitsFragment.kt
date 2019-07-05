package com.example.icloset


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_outfits.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class OutfitsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v =inflater.inflate(R.layout.fragment_outfits, container, false)

        var tops = ArrayList<String>()
        tops.add("All")
        tops.add("Work")
        tops.add("School")
        tops.add("Friends")
        tops.add("Wedding")
        tops.add("Party")
        tops.add("Restaurant")
        tops.add("Gym")
        tops.add("Trip")
        tops.add("Other")


        var adp = ArrayAdapter(activity,R.layout.layout_group,tops)

        v.lv_outfits.adapter = adp
        v.lv_outfits.setOnItemClickListener { parent, view, position, id ->

            when (position) {
                0 -> {
                    AppInfo.desc = "All"
                    MoveToFragment(OutfitItemsFragment())
                }
                1 -> {
                    AppInfo.desc = "Work"
                    MoveToFragment(OutfitItemsFragment())
                }
                2 -> {
                    AppInfo.desc = "School"
                    MoveToFragment(OutfitItemsFragment())
                }
                3 -> {
                    AppInfo.desc = "Friends"
                    MoveToFragment(OutfitItemsFragment())
                }
                4 -> {
                    AppInfo.desc = "Wedding"
                    MoveToFragment(OutfitItemsFragment())
                }
                5 -> {
                    AppInfo.desc = "Party"
                    MoveToFragment(OutfitItemsFragment())
                }
                6 -> {
                    AppInfo.desc = "Restaurant"
                    MoveToFragment(OutfitItemsFragment())
                }
                7 -> {
                    AppInfo.desc = "Gym"
                    MoveToFragment(OutfitItemsFragment())
                }
                8 -> {
                    AppInfo.desc = "Trip"
                    MoveToFragment(OutfitItemsFragment())
                }
                else -> {
                    AppInfo.desc = "Other"
                    MoveToFragment(OutfitItemsFragment())
                }

            }


        }

        v.fab_new.setOnClickListener {
            startActivity(Intent(activity,CreateOutfitActivity::class.java))
        }
        return v
    }

    private fun MoveToFragment(frg:Fragment){
        var trans = requireActivity().supportFragmentManager.beginTransaction()
        trans.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
        trans.replace(R.id.main_frame,frg)
        trans.addToBackStack(null)
        trans.commit()
    }
}
