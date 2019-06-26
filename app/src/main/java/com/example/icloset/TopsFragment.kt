package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

            AppInfo.type = "tops"
            when (position) {
                0 -> {
                    AppInfo.desc = "blazers"
                    MoveToFragment(ItemsFragment())
                }
                1 -> {
                    AppInfo.desc = "shirts"
                    MoveToFragment(ItemsFragment())
                }
                2 -> {
                    AppInfo.desc = "sweaters"
                    MoveToFragment(ItemsFragment())
                }
                3 -> {
                    AppInfo.desc = "tshirts"
                    MoveToFragment(ItemsFragment())
                }
                else -> {
                    AppInfo.desc = "sleeveless"
                    MoveToFragment(ItemsFragment())
                }
            }



        }

/*
        val recyclerView = v.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false)
        val cats = ArrayList<Categories>()
        var obj = icloset(activity)
           var db = obj.writableDatabase
           var cur= db.rawQuery("select * from item", arrayOf())
           if(cur.count == 0){
               Toast.makeText(activity,"No items", Toast.LENGTH_SHORT).show()

           }
           else{

               cur.moveToNext()
               while (!cur.isAfterLast){
                   cats.add(Categories(cur.getString(0)))
                   cur.moveToNext()
               }
           }



           val adapter = CustomAdapter(cats)
           recyclerView.adapter = adapter

    v.add_btn.setOnClickListener {
       db.execSQL("insert into im " +
               "(Type,Description,Weather,Times_worn,Occasion,Available,Item_image)" +
               " values ('Top','Shirt',1,0,'Party',1,'top_shirt1.png')", arrayOf())
       //db.execSQL("drop table item", arrayOf())
       Toast.makeText(activity,"done", Toast.LENGTH_SHORT).show()

   }*/
        return v
    }



    private fun MoveToFragment(frg:Fragment){

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_frame, frg)
            addToBackStack(null)
            setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
            commit()
        }
    }
}
