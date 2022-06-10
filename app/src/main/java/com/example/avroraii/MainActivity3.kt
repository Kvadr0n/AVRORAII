package com.example.avroraii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.io.BufferedReader
import java.io.FileReader

class MainActivity3 : AppCompatActivity()
{
    var viewId : TextInputEditText? = null
    var viewLogin : TextInputEditText? = null
    var viewPass : TextInputEditText? = null
    var dir : String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        dir = getExternalFilesDir(null).toString()
        viewId = findViewById(R.id.forgotID)
        viewLogin = findViewById(R.id.forgotLogin)
        viewPass = findViewById(R.id.forgotPassword)
    }

    fun onSwitch(view: View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onGet(view: View)
    {
        var id = viewId!!.text.toString()
        viewId!!.setText("")
        viewLogin!!.setText("")
        viewPass!!.setText("")
        var login : String?
        var pass : String?
        var line : String?
        var reader = BufferedReader(FileReader("$dir/Users.txt"))
        do
        {
            login = reader.readLine()
            if (login == null)
            {
                reader.close()
                val toast = Toast.makeText(applicationContext, "Неверный ID.", Toast.LENGTH_SHORT)
                toast.show()
                return
            }
            pass = reader.readLine()
            line = reader.readLine()
        }
        while (line != id)
        reader.close()
        viewLogin!!.setText(login)
        viewPass!!.setText(pass)
    }
}