package com.example.avroraii

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.io.*


class MainActivity : AppCompatActivity()
{
    var viewLogin : TextInputEditText? = null
    var viewPass : TextInputEditText? = null
    var remember : Boolean = false
    var dir : String = ""

    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null)
        {
            remember = savedInstanceState.getBoolean("remember", false)
            savedInstanceState.putBoolean("remember", remember)
        }
        dir = getExternalFilesDir(null).toString()
        var bw1 = BufferedWriter(FileWriter("$dir/People.txt", true))
        bw1.write("")
        bw1.close()
        var bw2 = BufferedWriter(FileWriter("$dir/Users.txt", true))
        bw2.write("")
        bw2.close()
        var bw3 = BufferedWriter(FileWriter("$dir/Remember.txt", true))
        bw3.write("")
        bw3.close()
        var reader = BufferedReader(FileReader("$dir/Remember.txt"))
        var line: String?
        line = reader.readLine()
        if (line != null)
        {
            var affinity = line
            var name = reader.readLine()
            var coursesN = Integer.parseInt(reader.readLine())
            var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
            var editor = sharedPreferences.edit()
            var course = ""
            var writer1 : BufferedWriter?
            for (i in 0..coursesN)
            {
                course = reader.readLine()
                editor.putString("courses$i", course)
                writer1 = BufferedWriter(FileWriter("$dir/$course.txt", true))
                writer1.write("")
                writer1.close()
            }
            reader.close()
            editor.putInt("coursesN", coursesN)
            editor.putBoolean("affinity", affinity == "t")
            editor.putString("name", name)
            editor.apply();
            val toast = Toast.makeText(applicationContext, "Добро пожаловать, $name!", Toast.LENGTH_SHORT)
            toast.show()
            var intent: Intent?
            if (affinity == "t")
                intent = Intent(this, TeacherActivity1::class.java)
            else
                intent = Intent(this, StudentActivity1::class.java)
            startActivity(intent)
        }
        reader.close()
        setContentView(R.layout.activity_main)
        viewLogin = findViewById(R.id.loginLogin)
        viewPass = findViewById(R.id.loginPassword)
    }

    fun onSwitch(view: View)
    {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

    fun onForgot(view: View)
    {
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
    }

    fun onRemember(view: View)
    {
        remember = !remember
    }

    fun onLogin(view: View)
    {
        var login = viewLogin!!.text.toString()
        var pass = viewPass!!.text.toString()
        if (login == "")
        {
            val toast = Toast.makeText(applicationContext, "Логин пустой.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (pass == "")
        {
            val toast = Toast.makeText(applicationContext, "Пароль пустой.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        var reader = BufferedReader(FileReader("$dir/Users.txt"))
        var line: String?
        do
            line = reader.readLine()
        while ((line != null) && (line != login))
        if (line == null)
        {
            val toast = Toast.makeText(applicationContext, "Неверный логин.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (pass != reader.readLine())
        {
            val toast = Toast.makeText(applicationContext, "Неверный пароль.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        var id = reader.readLine()
        reader.close()
        reader = BufferedReader(FileReader("$dir/People.txt"))
        do
            line = reader.readLine()
        while (line != id)
        var affinity = reader.readLine()
        var name = reader.readLine()
        var coursesN = Integer.parseInt(reader.readLine())
        --coursesN
        var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        var course = ""
        var writer = BufferedWriter(FileWriter("$dir/Remember.txt", false))
        writer.write("")
        var writer1 : BufferedWriter?
        if (remember)
        {
            writer.write(affinity)
            writer.newLine()
            writer.write(name)
            writer.newLine()
            writer.write("$coursesN")
            for (i in 0..coursesN)
            {
                course = reader.readLine()
                editor.putString("courses$i", course)
                writer.newLine()
                writer.write(course)
                writer1 = BufferedWriter(FileWriter("$dir/$course.txt", true))
                writer1.write("")
                writer1.close()
            }
        }
        else
            for (i in 0..coursesN)
            {
                course = reader.readLine()
                editor.putString("courses$i", course)
                writer1 = BufferedWriter(FileWriter("$dir/$course.txt", true))
                writer1.write("")
                writer1.close()
            }
        writer.close()
        reader.close()
        editor.putInt("coursesN", coursesN)
        editor.putBoolean("affinity", affinity == "t")
        editor.putString("name", name)
        editor.apply();
        val toast = Toast.makeText(applicationContext, "Добро пожаловать, $name!", Toast.LENGTH_SHORT)
        toast.show()
        var intent: Intent?
        if (affinity == "t")
            intent = Intent(this, TeacherActivity1::class.java)
        else
            intent = Intent(this, StudentActivity1::class.java)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        outState.putBoolean("remember", remember)
    }
}



/* Текст с контуром
        val layout = findViewById<View>(R.id.mainConstraint) as ConstraintLayout
        val set = ConstraintSet()
        val text = TextView(this)
        text.text = "amogus2"
        text.textSize = 36f
        text.setTextColor(Color.parseColor("#FFFFFF"))
        text.setShadowLayer(10f,0f,0f, 0xFF000000.toInt())
        text.id = View.generateViewId()
        layout.addView(text, 0)
        set.clone(layout)
        set.connect(text.id, ConstraintSet.TOP, layout.id, ConstraintSet.TOP, 60)
        set.connect(text.id, ConstraintSet.LEFT, layout.id, ConstraintSet.LEFT, 60)
        set.applyTo(layout)
*/