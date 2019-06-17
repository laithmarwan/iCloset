package com.example.icloset
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUserAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        register_button.setOnClickListener {
            if(register_email.text.toString() == "" || register_pass.text.toString() == "" || register_confirm.text.toString() == ""
                || register_address.text.toString() == "" ||  register_name.text.toString() == "" ){
                Toast.makeText(this,"Fields must not be empty!",
                    Toast.LENGTH_LONG).show()
            }




            else if (register_pass.text.toString() != register_confirm.text.toString()) {
                Toast.makeText(this,"Passwords don't match",Toast.LENGTH_LONG).show()
            } else {


                var pd = ProgressDialog(this)
                pd.setMessage("Please Wait...")
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                pd.show()

                var rq = Volley.newRequestQueue(this)
                var sr = object : StringRequest(
                    Request.Method.POST, AppInfo.web + "signup.php",
                    Response.Listener { response ->
                        pd.hide()
                        if(response != "0"){
                            AppInfo.Email = register_email.text.toString()
                            AppInfo.Address = register_address.text.toString()
                            AppInfo.Name = register_name.text.toString()
                            AppInfo.UserID = response
                            var i = Intent(this, MainActivity::class.java)
                            Toast.makeText(this,"Welcome " + register_name.text.toString() +"!",Toast.LENGTH_LONG).show()
                            startActivity(i)
                            finish()
                        }
                        else if(response == "-1"){
                            Toast.makeText(this,"User already exists",Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(this,"An error occurred",Toast.LENGTH_LONG).show()
                        }

                    },
                    Response.ErrorListener { error ->
                        pd.hide()
                        Toast.makeText(
                            this, error.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }) {
                    override fun getParams(): MutableMap<String, String> {
                        var map = HashMap<String, String>()
                        if(radioButton5.isChecked){
                            map.put("gender", "1")
                        }
                        else{
                            map.put("gender", "0")
                        }
                        map.put("email", register_email.text.toString())
                        map.put("password", register_pass.text.toString())
                        map.put("name", register_name.text.toString())
                        map.put("address", register_address.text.toString())

                        return map
                    }
                }

                rq.add(sr)


            }
        }

    }
}
