package com.example.icloset


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


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

        val header: MutableList<String> = ArrayList<String>()
        val body: MutableList<MutableList<String>> = ArrayList()

        val Tops: MutableList<String> = ArrayList()
        Tops.add("Blazers")
        Tops.add("Shirt")
        Tops.add("Sweaters")
        Tops.add("T-Shirts")
        Tops.add("Sleeveless")



        val Bottoms: MutableList<String> = ArrayList()
        Bottoms.add("The North Remembers")
        Bottoms.add("The Night Lands")
        Bottoms.add("What is Dead May Never Die")
        Bottoms.add("Garden of Bones")
        Bottoms.add("The Ghost of Harrenhal")
        Bottoms.add("The Old Gods and the New")
        Bottoms.add("A Man Without Honor")
        Bottoms.add("The Prince of Winterfell")
        Bottoms.add("Blackwater")
        Bottoms.add("Valar Morghulis")

        val Shoes: MutableList<String> = ArrayList()
        Shoes.add("Valar Dohaeris")
        Shoes.add("Dark Wings, Dark Words")
        Shoes.add("Walk of Punishment")
        Shoes.add("And Now His Watch is Ended")
        Shoes.add("Kissed by Fire")
        Shoes.add("The Climb")
        Shoes.add("The Bear and the Maiden Fair")
        Shoes.add("Second Sons")
        Shoes.add("The Rains of Castamere")
        Shoes.add("Mhysa")


        val Dresses: MutableList<String> = ArrayList()
        Dresses.add("Two Swords")
        Dresses.add("The Lion and the Rose")
        Dresses.add("Breaker of Chains")
        Dresses.add("Oathkeeper")
        Dresses.add("First of His Name")
        Dresses.add("The Laws of Gods and Men")
        Dresses.add("Mockingbird")
        Dresses.add("The Mountain and the Viper")
        Dresses.add("The Watchers on the Wall")
        Dresses.add("The Children")



        val Bags: MutableList<String> = ArrayList()
        Bags.add("The Wars to Come")
        Bags.add("The House of Black and White")
        Bags.add("High Sparrow")
        Bags.add("Sons of the Harpy")
        Bags.add("Kill the Boy")
        Bags.add("Unbowed, Unbent, Unbroken")
        Bags.add("The Gift")
        Bags.add("Hardhome")
        Bags.add("The Dance of Dragons")
        Bags.add("Mother's Mercy")

        if(AppInfo.Gender == "0"){
            header.add("Dresses")
            header.add("Bags")
            body.add(Dresses)
            body.add(Bags)
        }
        val Accessories: MutableList<String> = ArrayList()
        Accessories.add("The Red Woman")
        Accessories.add("Home")
        Accessories.add("Oathbreaker")
        Accessories.add("Book of the Stranger")
        Accessories.add("The Door")
        Accessories.add("Blood of My Blood")
        Accessories.add("The Broken Man")
        Accessories.add("No One")
        Accessories.add("Battle of the Bastards")
        Accessories.add("The Winds of Winter (69 min)")


        val Outerwear: MutableList<String> = ArrayList()
        Outerwear.add("Dragonstone")
        Outerwear.add("Stormborn")
        Outerwear.add("The Queen's Justice")
        Outerwear.add("The Spoils of War")
        Outerwear.add("Eastwatch")
        Outerwear.add("Beyond the Wall")
        Outerwear.add("The Dragon and the Wolf")



        header.add("Tops")
        header.add("Bottoms")
        header.add("Shoes")
        header.add("Accessories")
        header.add("Outerwear")


        body.add(Tops)
        body.add(Bottoms)
        body.add(Shoes)
        body.add(Accessories)
        body.add(Outerwear)

        //  v.expandableListView.setAdapter(ExpandableListAdapter(activity,expandableListView, header, body))

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


}
