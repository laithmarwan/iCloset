package com.example.icloset
import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_choose_item.*
import kotlinx.android.synthetic.main.fragment_choose_item.view.*
import java.io.File
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ChooseItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v =inflater.inflate(R.layout.fragment_choose_item, container, false)
        var arr = ArrayList<String>()
        arr.add("Tops")
        arr.add("Bottoms")
        arr.add("Shoes")
        if(AppInfo.Gender == "0")
            arr.add("Dresses")
        arr.add("Bags")
        arr.add("Accessories")
        arr.add("Outerwear")
        var adp = ArrayAdapter(activity,R.layout.layout_group,arr)
        v.lv.adapter = adp

        v.lv.setOnItemClickListener { parent, view, position, id ->
            val intent: Intent = requireActivity().intent
            when (position) {
                0 -> {
                    intent.putExtra("Type","Tops")

                }
                1 -> {
                    intent.putExtra("Type","Bottoms")

                }
                2 -> {
                    intent.putExtra("Type","Shoes")

                }
                3 -> {
                    if(AppInfo.Gender == "0"){
                        intent.putExtra("Type","Dresses")}
                    else{
                        intent.putExtra("Type","Bags")}
                    //startActivityForResult(Intent(this,))

                }
                4 -> {
                    if(AppInfo.Gender == "0")
                        intent.putExtra("Type","Bags")
                    else
                        intent.putExtra("Type","Accessories")

                }
                5 -> {
                    if(AppInfo.Gender == "0")
                        intent.putExtra("Type","Accessories")
                    else
                        intent.putExtra("Type","Outerwear")

                }
                else -> {
                    intent.putExtra("Type","Outerwear")

                }


            }

            requireActivity().setResult(Activity.RESULT_OK,intent)
            requireActivity().finish()



        }



        return v
    }


}
