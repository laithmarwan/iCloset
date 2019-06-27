package com.example.icloset
import android.app.PendingIntent.getActivity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUserAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)


        radioButton3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                background.setBackgroundResource(R.drawable.gradient_male)

            }
        })

        radioButton5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                background.setBackgroundResource(R.drawable.gradient_female)

            }
        })

        register_button.setOnClickListener {


            if(register_email.text.toString() == "" || register_pass.text.toString() == "" || register_confirm.text.toString() == ""
                || register_address.text.toString() == "" ||  register_name.text.toString() == "" ){
                Toast.makeText(this,"Fields must not be empty!",
                    Toast.LENGTH_LONG).show()
            }
            else if (register_pass.text.toString() != register_confirm.text.toString()) {
                Toast.makeText(this,"Passwords don't match",Toast.LENGTH_LONG).show()
            } else if (register_pass.text.toString().length < 8) {
                Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_LONG).show()

            }else{


                var pd = ProgressDialog(this)
                pd.setMessage("Please Wait...")
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                pd.show()

                var rq = Volley.newRequestQueue(this)

                var jo = JsonObjectRequest(Request.Method.GET,
                    "https://api.emailverifyapi.com/v3/lookups/json?key=512BE052EAFFBB11&email="+register_email.text.toString(),
                    null,Response.Listener {
                        response ->
                            if(response.getString("deliverable") != "true")
                            {
                                pd.hide()
                                tv_hidden.text = "ok"
                                Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show()
                            }



                    },Response.ErrorListener {
                        error ->
                        pd.hide()
                        Toast.makeText(
                            this, error.message,
                            Toast.LENGTH_LONG
                        ).show()
                    })

                rq.add(jo)

                if(tv_hidden.text.toString() == "ok"){
                    tv_hidden.text = ""

                    var sr = object : StringRequest(
                    Request.Method.POST, AppInfo.web + "signup.php",
                    Response.Listener { response ->
                        pd.hide()
                        if(response != "0"){
                            AppInfo.Email = register_email.text.toString()
                            AppInfo.Address = register_address.text.toString()
                            AppInfo.Name = register_name.text.toString()
                            AppInfo.UserID = response
                            if(radioButton5.isChecked){
                                AppInfo.Gender = "0"
                            }
                            else{
                                AppInfo.Gender = "1"
                            }
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
                            map.put("gender", "0")
                        }
                        else{
                            map.put("gender", "1")
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
}
