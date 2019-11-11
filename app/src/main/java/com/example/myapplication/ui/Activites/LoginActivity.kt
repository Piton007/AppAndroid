package com.example.myapplication.ui.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.networking.model.Driver
import com.example.networking.networking.SmartParkApi
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var username: String = ""
    private var password: String = ""
    private var usernamevalid: Boolean = false
    private var passwordvalid: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar()?.hide();
        setContentView(R.layout.activity_login)
        val register=findViewById<TextView>(R.id.txtRegister)
        register.setOnClickListener{
            intent = Intent(this,MainActivity::class.java)

            intent.putExtra("name","helo")
            intent.putExtra("password","hello")
            intent.putExtra("email","hello")
            startActivity(intent)
            //finish()
        }
        val inputEmail=findViewById<TextView>(R.id.inputEmail)
        inputEmail.requestFocus()

    }
    override fun onResume() {
        super.onResume()

        login.setOnClickListener{
            username = inputEmail.text.toString()
            password = inputPassword.text.toString()

            username.apply {
                if(this.isBlank()){
                    usernamevalid = false
                }
                else{
                    usernamevalid = true
                }
            }
            password.apply {
                if(this.isBlank()){
                    passwordvalid = false
                }
                else{
                    passwordvalid = true
                }
            }

            if(passwordvalid and usernamevalid){
                var list: ArrayList<Driver>?
                SmartParkApi.getDrivers({
                    it?.forEach {
                        val driver= it
                        if(it.email.toString()== username){
                            if(it.dni.toString() == password){
                                val login = findViewById<Button>(R.id.login)
                                login.setOnClickListener {
                                    val view=it
                                    intent = Intent(this,MainActivity::class.java)

                                    intent.putExtra("name",username)
                                    intent.putExtra("password",password)
                                    intent.putExtra("email",driver.email)
                                    startActivity(intent) }
                            }

                        }
                        else{
                        }
                    }
                },{
                    Log.d("NEARMETESTNewsApi", "${it.errorBody} ${it.localizedMessage}")
                }
                )
            }
        }
    }
}
