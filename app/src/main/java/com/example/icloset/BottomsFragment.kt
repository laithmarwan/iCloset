package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_bottoms.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class BottomsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         var v = inflater.inflate(R.layout.fragment_bottoms, container, false)

        var tops = ArrayList<String>()
        tops.add("Trousers")
        tops.add("Shorts")
        tops.add("Jeans")
        if(AppInfo.Gender == "0")
            tops.add("Skirts")


        var adp = ArrayAdapter(activity,R.layout.layout_group,tops)
        v.lv_bottoms.adapter = adp



        v.lv_bottoms.setOnItemClickListener { parent, view, position, id ->
            AppInfo.type = "bottoms"
            when (position) {
                0 -> {
                    AppInfo.desc = "trousers"
                    MoveToFragment(ItemsFragment())
                }
                1 -> {
                    AppInfo.desc = "shorts"
                    MoveToFragment(ItemsFragment())
                }
                2 -> {
                    AppInfo.desc = "jeans"
                    MoveToFragment(ItemsFragment())
                      }
                else -> {
                    AppInfo.desc = "skirts"
                    MoveToFragment(ItemsFragment())
                }
            }




        }
        return v
    }
    private fun MoveToFragment(frg:Fragment){
        var trans = requireActivity().supportFragmentManager.beginTransaction()
        var obj = frg
        trans.replace(R.id.vp,obj)
        trans.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
        trans.addToBackStack(null)
        trans.commit()
    }

}
