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

class LoginAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
                    if(response=="1") {
                        AppInfo.Email = login_email.text.toString()
                        var i = Intent(this, MainActivity::class.java)
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
