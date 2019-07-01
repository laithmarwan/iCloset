package com.example.icloset
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_new_outfit.*
import kotlinx.android.synthetic.main.fragment_new_outfit.view.*
import java.io.File


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NewOutfitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var v = inflater.inflate(R.layout.fragment_new_outfit, container, false)

        v.button.setOnClickListener{
            startActivityForResult(Intent(activity,ChooseItemActivity::class.java),1010)
        }
        return v
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1010) {
            val draggableBox = DraggableBox(requireContext())

            // Creating a LinearLayout.LayoutParams object for text view
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // This will define text view width
                LinearLayout.LayoutParams.WRAP_CONTENT // This will define text view height
            )

            // Add margin to the text view
            params.setMargins(10, 10, 10, 10)

            // Now, specify the text view width and height (dimension)
            draggableBox.layoutParams = params

            // Display some text on the newly created text view
            var obj = icloset(requireActivity())
            var db = obj.readableDatabase
            var cur = db.rawQuery("select * from item where Type=? and Description = ?", arrayOf("Tops", "Shirts"))
            if (cur.count == 0) {
                Toast.makeText(activity, "No items in this category", Toast.LENGTH_SHORT).show()
            } else {
                cur.moveToFirst()

                val storageDirectory = Environment.getExternalStorageDirectory().toString()

                val file = File(storageDirectory, cur.getString(cur.getColumnIndex("Item_image")))
                draggableBox.setImageURI(Uri.parse(file.absolutePath))

            }

            root_layout.addView(draggableBox)

        }
    }
}
