package com.example.icloset


import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.MultiSelectListPreference
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_my_account.*
import kotlinx.android.synthetic.main.fragment_my_account.view.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MyAccount : Fragment() {

    lateinit var option : Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_my_account, container, false)
        if(AppInfo.Gender=="0")
        {

            v.gender_toggle.isChecked = false
            v.gender_toggle.textOff = "Female"
            v.gender_toggle.text = "Female"
            v.gender_toggle.setBackgroundResource(R.drawable.round_corners_button_female)
        }
        else
        {

            v.gender_toggle.isChecked = true
            v.gender_toggle.textOn = "Male"
            v.gender_toggle.text = "Male"
            v.gender_toggle.setBackgroundResource(R.drawable.round_corners_button_male)
        }
        v.gender_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                v.gender_toggle.setBackgroundResource(R.drawable.round_corners_button_male)
                v.gender_toggle.text = "Male"
            } else {
                v.gender_toggle.setBackgroundResource(R.drawable.round_corners_button_female)
                v.gender_toggle.text = "Female"
            }
        }

        var flag_address = false
        //v.email_view.text = AppInfo.Email
        v.account_name.hint = AppInfo.Name
        v.address.hint = AppInfo.Address

        /*if(AppInfo.Gender == "0"){
            v.gender_toggle.text = "Female"
        }
        else if(AppInfo.Gender == "1"){
            v.gender_toggle.text = "Male"
        }*/

        var opt = ""
       /* var toggle = v.findViewById(R.id.gender_toggle) as ToggleButton
        var result = ""

        option = v.findViewById(R.id.city_spinner) as Spinner
        val options = ArrayList<String>()
        /*options.add(AppInfo.Address)
        *//*options.add("Option1")
        options.add("Option2")*/

        option.adapter = ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                opt = option.getItemAtPosition(position).toString()

            }
        }*/

        v.address_view.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val addressArray = arrayOf("Amman","London")


            builder.setTitle("Select your address")
            builder.setSingleChoiceItems(addressArray, -1){ dialog: DialogInterface, which: Int ->
                v.address.hint = addressArray[which]
                opt = addressArray[which]

            }
            builder.setPositiveButton("OK"){dialog, which ->
                flag_address = true
                //database
            }
            builder.setNegativeButton("Cancel"){dialog, which ->
                v.address.hint = AppInfo.Address
                dialog.dismiss()
                flag_address = false
            }
            val dialog = builder.create()
            dialog.show()
        }


        v.save_changes.setOnClickListener {
            var flag_name = false
            var flag_pass = false
            var flag_male = false
            var flag_female = false


            if(v.account_name.text.toString()!= ""){
                flag_name = true
            }
            if(old_password.text.toString() != "") {
                if (v.new_password.text.toString() == v.confirm_password.text.toString()) {
                    if (v.new_password.text.length > 7)
                        flag_pass = true
                    else {
                        Toast.makeText(activity, "Insert at least 8 characters", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    Toast.makeText(activity, "Passwords don't match", Toast.LENGTH_LONG).show()
                }
            }
            if(opt == AppInfo.Address){
                flag_address = false
            }


            if (AppInfo.Gender == "0" && v.gender_toggle.isChecked){
                flag_male = true
            }
            else if (AppInfo.Gender == "1" && !v.gender_toggle.isChecked){
                flag_female = true
            }


            if(flag_name || flag_pass || flag_address || flag_male || flag_female){ //|| flag_male || flag_female
                val builder = android.app.AlertDialog.Builder(activity)
                builder.setTitle("Alert")
                builder.setMessage("Are you sure?")
                builder.setPositiveButton("Yes"){dialog, which ->
                    // database update
                    var pd = ProgressDialog(activity)
                    pd.setMessage("Please Wait...")
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    pd.show()
                    var rq2 = Volley.newRequestQueue(activity)
                    var sr = object : StringRequest(
                        Request.Method.POST, AppInfo.web + "updateinfo.php",
                        Response.Listener { response ->
                            pd.hide()
                            if(response != "-1"){
                                var arr = response.split("/")
                                for ( i in 0 until arr.size){
                                    if(arr[i] == "name"){
                                        AppInfo.Name = v.account_name.text.toString()
                                        Toast.makeText(activity, "Name changed", Toast.LENGTH_LONG).show()
                                    }
                                    else if(arr[i] == "address"){
                                        AppInfo.Address = opt
                                        Toast.makeText(activity, "Address changed", Toast.LENGTH_LONG).show()
                                    }
                                    else if(arr[i] == "pass"){
                                        Toast.makeText(activity,"Password changed successfully",Toast.LENGTH_LONG).show()
                                    }
                                    else if(arr[i] == "gender"){
                                        Toast.makeText(activity,"Gender changed successfully",Toast.LENGTH_LONG).show()
                                        if(AppInfo.Gender == "0")
                                            AppInfo.Gender = "1"
                                        else{
                                            AppInfo.Gender = "0"
                                        }
                                    }
                                }

                                var i = Intent(activity, MainActivity::class.java)
                                startActivity(i)

                            }
                            else if(response == "0"){
                                Toast.makeText(activity,"Error",Toast.LENGTH_LONG).show()
                            }
                            else{
                                Toast.makeText(activity,"Incorrect password",Toast.LENGTH_LONG).show()
                            }

                        },
                        Response.ErrorListener { error ->
                            pd.hide()
                            Toast.makeText(
                                activity, error.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }) {
                        override fun getParams(): MutableMap<String, String> {
                            var map = HashMap<String, String>()
                            if(flag_name)
                                map.put("name", v.account_name.text.toString())
                            if(flag_address)
                                map.put("address", opt)
                            if(flag_pass) {
                                map.put("password", v.old_password.text.toString())
                                map.put("new", v.new_password.text.toString())
                            }
                            if(flag_male){
                                map.put("gender","1")
                            }
                            if(flag_female){
                                map.put("gender","0")
                            }
                            map.put("email",AppInfo.Email)

                            return map
                        }
                    }

                    rq2.add(sr)
                }
                builder.setNegativeButton("No"){
                        dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
            }
            else{
                Toast.makeText(activity,"No changes detected", Toast.LENGTH_SHORT).show()
            }
        }

        v.cancel.setOnClickListener {
            var i = Intent(activity, MainActivity::class.java)
            startActivity(i)
        }
        return v
    }
}
