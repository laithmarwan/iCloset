package com.example.icloset


import android.app.AlertDialog
import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_settings.view.*
import android.security.ConfirmationPrompt
import android.widget.Toast
import kotlinx.android.synthetic.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_settings, container, false)

        v.iv_update.setOnClickListener {
            Toast.makeText(activity,AppInfo.UserID,Toast.LENGTH_SHORT).show()
        }

        v.iv_logout.setOnClickListener {

            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Alert")
            builder.setMessage("Are you sure?")
            builder.setPositiveButton("Yes"){dialog, which ->
                AppInfo.UserID=""
                AppInfo.Name=""
                AppInfo.Email=""
                AppInfo.Address=""
                var sp= this.getActivity().getSharedPreferences("user_data",
                    Context.MODE_PRIVATE)
                var editor = sp.edit()
                editor.putString("user_id","")
                editor.commit()
                var i = Intent(activity, LoginAct::class.java)
                startActivity(i)
                getActivity().finish();
            }
            builder.setNegativeButton("No"){
                dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }
        return v
    }


}
