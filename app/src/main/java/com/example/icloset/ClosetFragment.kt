package com.example.icloset


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        var adp = ClosetFragment.FPA(requireActivity().supportFragmentManager)
        v.vp.adapter = adp

        v.vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        v.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(v.vp))


        return v
    }

    class FPA(fm: FragmentManager) : FragmentPagerAdapter(fm)
    {
        override fun getItem(position: Int): Fragment {

            return when (position) {
                0 -> TopsFragment()
                1 -> BottomsFragment()
                2 -> ShoesFragment()
                3 -> DressesFragment()
                4 -> BagsFragment()
                5 -> AccessoriesFragment()
                else -> OuterwearFragment()

            }

        }

        override fun getCount(): Int {
            return 7
        }

    }

}
