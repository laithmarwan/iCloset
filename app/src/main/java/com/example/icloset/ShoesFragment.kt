package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_shoes.*
import kotlinx.android.synthetic.main.fragment_shoes.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ShoesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_shoes, container, false)

        var tops = ArrayList<String>()
        if(AppInfo.Gender == "0"){
            tops.add("Boots")
            tops.add("Flats")
            tops.add("Heels")
            tops.add("Sandals")
        }
        else{
            tops.add("Boots")
            tops.add("Boat Shoes")
            tops.add("Sneakers")
            tops.add("Trainers")
        }


        var adp = ArrayAdapter(activity,R.layout.layout_group,tops)

        v.lv_shoes.adapter = adp

          v.lv_shoes.setOnItemClickListener { parent, view, position, id ->
              AppInfo.type = "Shoes"
              when (position) {
                  0 -> {
                      AppInfo.desc = "Boots"
                      MoveToFragment(ItemsFragment())
                  }
                  1 -> {
                      if(AppInfo.Gender == "0")
                          AppInfo.desc = "Flats"
                      else
                          AppInfo.desc = "Boat Shoes"
                      MoveToFragment(ItemsFragment())
                  }
                  2 -> {
                      if(AppInfo.Gender == "0")
                          AppInfo.desc = "Heels"
                      else
                          AppInfo.desc = "Sneakers"
                      MoveToFragment(ItemsFragment())
                  }
                else -> {
                    if(AppInfo.Gender == "0")
                        AppInfo.desc = "Sandals"
                    else
                        AppInfo.desc = "Trainers"
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
        AppInfo.act = "closet"
        trans.commit()
    }

}
