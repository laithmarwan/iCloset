package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_outerwear.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class OuterwearFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_outerwear, container, false)

        var tops = ArrayList<String>()

        tops.add("Jackets")
        tops.add("Coats")

        var adp = ArrayAdapter(activity,R.layout.layout_group,tops)

        v.lv_outer.adapter = adp

      v.lv_outer.setOnItemClickListener { parent, view, position, id ->

          AppInfo.type = "outerwear"
          when (position) {
              0 -> {
                  AppInfo.desc = "jackets"
                  MoveToFragment(ItemsFragment())
              }
              else -> {
                  AppInfo.desc = "coats"
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
