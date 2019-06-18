package com.example.icloset

import android.app.ProgressDialog
import android.content.Context
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

class LoginAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var sp=getSharedPreferences("user_data",
            Context.MODE_PRIVATE)
        if (sp.getString("user_id","")!="")
        {
            var i=Intent(this,MainActivity::class.java)
            AppInfo.UserID = sp.getString("user_id","")
            startActivity(i)
            finish()
        }
        textView5.setOnClickListener {

            startActivity(Intent(this, NewUserAct::class.java))
        }


        imageView5.setOnClickListener {
            if(login_email.text.toString() == "" || login_pass.text.toString() == "" ){
                Toast.makeText(this,"Fields must not be empty!",
                    Toast.LENGTH_LONG).show()
            }
            else{
            var pd= ProgressDialog(this)
            pd.setMessage("Please Wait...")
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            pd.show()

            var rq= Volley.newRequestQueue(this)
            var sr=object: StringRequest(

                Request.Method.POST,AppInfo.web+"login.php",
                Response.Listener { response ->
                    pd.hide()
                    if(response!="0") {
                        var arr = response.split(",")

                        AppInfo.Email = login_email.text.toString()
                        AppInfo.UserID = arr[0]
                        AppInfo.Gender = arr[1]
                        AppInfo.Name = arr[2]
                        AppInfo.Address = arr[3]
                        var i = Intent(this, MainActivity::class.java)
                        var sp=getSharedPreferences("user_data",
                            Context.MODE_PRIVATE)
                        var editor = sp.edit()
                        editor.putString("user_id",response)
                        editor.commit()
                        Toast.makeText(this,"Welcome back" +"!",Toast.LENGTH_LONG).show()
                        startActivity(i)
                        finish()
                    }
                    else
                        Toast.makeText(this,"Login Failed",
                            Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener { error ->
                    pd.hide()
                    Toast.makeText(this,error.message,
                        Toast.LENGTH_LONG).show()
                })
            {
                override fun getParams(): MutableMap<String, String> {
                    var map=HashMap<String,String>()

                    map.put("Email",login_email.text.toString())
                    map.put("Password",login_pass.text.toString())

                    return map
                }
            }

            rq.add(sr)

        }}

    }
}
