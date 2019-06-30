package com.example.icloset
import android.app.ActionBar
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
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
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
//    lateinit var parentLinearLayout: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var v =inflater.inflate(R.layout.fragment_choose_item, container, false)

        v.button.setOnClickListener{
            val image_view = DraggableBox(requireContext())

            // Creating a LinearLayout.LayoutParams object for text view
            var params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // This will define text view width
                LinearLayout.LayoutParams.WRAP_CONTENT // This will define text view height
            )

            // Add margin to the text view
            params.setMargins(10,10,10,10)

            // Now, specify the text view width and height (dimension)
            image_view.layoutParams = params

            // Display some text on the newly created text view
            var obj = icloset(requireActivity())
            var db = obj.readableDatabase
            var cur = db.rawQuery("select * from item where Type=? and Description = ?", arrayOf("Tops","Shirts"))
            if(cur.count ==0){
                Toast.makeText(activity,"No items in this category",Toast.LENGTH_SHORT).show()
            }
            else{
                cur.moveToFirst()

                    val storageDirectory = Environment.getExternalStorageDirectory().toString()

                    val file = File(storageDirectory,cur.getString(cur.getColumnIndex("Item_image")))
                    image_view.setImageURI(Uri.parse(file.absolutePath))
                    
            }

            root_layout.addView(image_view)

    }

        return v
    }


}
