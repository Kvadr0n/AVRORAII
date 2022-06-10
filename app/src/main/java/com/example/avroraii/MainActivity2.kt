package com.example.avroraii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.io.*

class MainActivity2 : AppCompatActivity()
{
    var viewId : TextInputEditText? = null
    var viewLogin : TextInputEditText? = null
    var viewPass : TextInputEditText? = null
    var viewConf : TextInputEditText? = null
    var dir : String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        dir = getExternalFilesDir(null).toString()
        viewId = findViewById(R.id.registerId)
        viewLogin = findViewById(R.id.registerLogin)
        viewPass = findViewById(R.id.registerPassword)
        viewConf = findViewById(R.id.registerConfirm)
    }

    fun onSwitch(view: View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onRegister(view: View)
    {
        var id = viewId!!.text.toString()
        var reader = BufferedReader(FileReader("$dir/People.txt"))
        var line : String?
        do
            line = reader.readLine()
        while ((line != null) && (line != id))
        if (line == null)
        {
            reader.close()
            val toast = Toast.makeText(applicationContext, "Неверный ID.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        var reader1 = BufferedReader(FileReader("$dir/Users.txt"))
        do
            line = reader1.readLine()
        while ((line != null) && (line != id))
        reader1.close()
        if (line == id)
        {
            val toast = Toast.makeText(applicationContext, "Пользователь уже зарегистрирован.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        var affinity = reader.readLine()
        var name = reader.readLine()
        if (viewLogin!!.text.toString() == "")
        {
            val toast = Toast.makeText(applicationContext, "Логин пустой.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        var pass = viewPass!!.text.toString()
        if (pass == "")
        {
            val toast = Toast.makeText(applicationContext, "Пароль пустой.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (pass != viewConf!!.text.toString())
        {
            val toast = Toast.makeText(applicationContext, "Пароли не совпадают.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        var reader2 = BufferedReader(FileReader("$dir/Users.txt"))
        var login = viewLogin!!.text.toString()
        do
        {
            line = reader2.readLine()
            reader2.readLine()
            reader2.readLine()
        }
        while ((line != null) && (line != login))
        if (line == login)
        {
            val toast = Toast.makeText(applicationContext, "Логин уже использован.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        var file = File("$dir/Users.txt")
        var writer = BufferedWriter(FileWriter("$dir/Users.txt", true))
        if (file.length() > 0)
            writer.newLine()
        writer.write(login)
        writer.newLine()
        writer.write(pass)
        writer.newLine()
        writer.write(id)
        writer.close()
        var coursesN = Integer.parseInt(reader.readLine())
        --coursesN
            /*
        var sharedPreferences = getDefaultSharedPreferences(applicationContext)
        var editor = sharedPreferences.edit()
        for (i in 0..coursesN)
            editor.putString("courses$i", reader.readLine())
             */
        reader.close()
        /*
        editor.putInt("coursesN", coursesN)
        editor.putBoolean("affinity", affinity == "t")
         */
        viewId!!.setText("")
        viewLogin!!.setText("")
        viewPass!!.setText("")
        viewConf!!.setText("")
        val toast = Toast.makeText(applicationContext, "Поздравляем с регистрацией, $name!", Toast.LENGTH_SHORT)
        toast.show()
        return
    }
}