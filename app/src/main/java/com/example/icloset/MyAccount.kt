package com.example.icloset


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_my_account.view.*
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
        v.email_view.text = AppInfo.Email
        v.account_name.hint = AppInfo.Name

        var toggle = v.findViewById(R.id.gender_toggle) as ToggleButton
        var result = ""

        option = v.findViewById(R.id.city_spinner) as Spinner
        val options = ArrayList<String>()
        options.add(AppInfo.Address)
        options.add("Option1")
        options.add("Option2")

        //val options = arrayOf("Option1", "Option2")
        option.adapter = ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1, options)


        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {



                v.save_changes.setOnClickListener {
                    var flag_name:Boolean = false
                    var flag_pass:Boolean = false
                    var flag_address:Boolean = false

                    if(v.account_name.hint.toString().equals(AppInfo.Name)){
                        // do nothing
                        // Toast.makeText(activity,"do nothing1", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        flag_name = true
                    }

                    if(v.new_password.text.toString().equals(v.confirm_password.text.toString())){
                        // do nothing
                        // Toast.makeText(activity,"do nothing2", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        flag_pass = true
                    }

                    if(option.getItemAtPosition(position).equals(AppInfo.Address)){
                        //do nothing
                        // Toast.makeText(activity,"do nothing3", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        flag_address = true
                    }



                    if(flag_name == true || flag_pass == true || flag_address == true){
                        val builder = android.app.AlertDialog.Builder(activity)
                        builder.setTitle("Alert")
                        builder.setMessage("Are you sure?")
                        builder.setPositiveButton("Yes"){dialog, which ->
                            // database update
                            Toast.makeText(activity,"database", Toast.LENGTH_SHORT).show()
                        }
                        builder.setNegativeButton("No"){
                                dialog, which ->
                            dialog.dismiss()
                        }
                        builder.show()
                    }
                    else{
                        Toast.makeText(activity,"There is no changing", Toast.LENGTH_SHORT).show()
                    }
                }

                v.cancel.setOnClickListener {
                    var i = Intent(activity, MainActivity::class.java)
                    startActivity(i)
                }
            }
        }
        return v
    }
}
