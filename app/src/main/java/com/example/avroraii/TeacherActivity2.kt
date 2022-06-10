package com.example.avroraii

import android.content.Intent
import android.graphics.Point
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import java.io.*

class TeacherActivity2 : AppCompatActivity()
{
    var coursesN : Int? = null
    var testN : Int? = null
    var clicked = false
    var dir : String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher2)
        supportActionBar!!.title = "Режим Преподавателя"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        dir = getExternalFilesDir(null).toString()
        var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
        var curCourse = sharedPreferences.getString("curCourse", "no")
        var reader = BufferedReader(FileReader("$dir/$curCourse.txt"))
        var line : String?
        while (true)
        {
            line = reader.readLine()
            if (line == null)
                break
            createButton(line)
        }
        reader.close()
        var point = Point()
        windowManager.defaultDisplay.getSize(point)
        var heightScreen = point.y;
        var button = findViewById<Button>(R.id.pseudoButton)
        button.measure(0,0)
        var heightButton = button.measuredHeight
        val text = TextView(this)
        text.text = "                                                                              "
        text.height = (heightScreen - 2.1f * heightButton).toInt()
        text.id = View.generateViewId()
        var layout = findViewById<LinearLayout>(R.id.linearLayout)
        layout.addView(text, 0)
        var scrollview = findViewById<ScrollView>(R.id.scrollview)
        scrollview.post { scrollview.fullScroll(ScrollView.FOCUS_DOWN) }
    }

    override fun onSupportNavigateUp(): Boolean
    {
        if (!clicked)
        {
            clicked = true
            val toast = Toast.makeText(applicationContext, "Нажмите ещё раз для выхода.", Toast.LENGTH_SHORT)
            toast.show()
        }
        else
        {
            var writer = BufferedWriter(FileWriter("$dir/Remember.txt", false))
            writer.write("")
            writer.close()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    fun createButton(course : String?)
    {
        var layout = findViewById<LinearLayout>(R.id.linearLayout)
        var button = Button(this)
        button.text = course;
        button.setTextColor(4294967295.toInt())
        button.height = findViewById<Button>(R.id.pseudoButton).height
        button.setBackgroundColor(4293961300.toInt())
        button.setOnClickListener{onClick(button)}
        layout.addView(button, 0)
    }

    fun onClick(button : Button)
    {
        var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
        var course = sharedPreferences.getString("curCourse", "no")
        var testName = button.text.toString()
        var editor = sharedPreferences.edit()
        editor.putString("title", testName)
        editor.apply()
        var intent = Intent(this, TeacherActivity4::class.java)
        startActivity(intent)
    }

    fun onMake(view: View)
    {
        var text = findViewById<TextInputEditText>(R.id.title).text.toString()
        if (text == "")
        {
            val toast = Toast.makeText(applicationContext, "Недопустимое название теста.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        var testName = text
        text = findViewById<TextInputEditText>(R.id.answers).text.toString()
        if ((text == "") || (text[0] == '0'))
        {
            val toast = Toast.makeText(applicationContext, "Недопустимое количество вопросов.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
        var course = sharedPreferences.getString("curCourse", "no")
        var bw1 = BufferedWriter(FileWriter("$dir/$course$testName.txt", true))
        bw1.write("")
        if (File("$dir/$course$testName.txt").length() > 0)
        {
            val toast = Toast.makeText(applicationContext, "Тест с таким названием уже существует.", Toast.LENGTH_SHORT)
            toast.show()
            bw1.close()
            return
        }
        bw1.write(text)
        bw1.close()
        var bw2 = BufferedWriter(FileWriter("$dir/$course$testName-stat.html", true))
        bw2.write("")
        bw2.close()
        var editor = sharedPreferences.edit()
        editor.putString("title", testName)
        editor.putInt("questionsN", Integer.parseInt(text))
        editor.apply()
        intent = Intent(this, TeacherActivity3::class.java)
        startActivity(intent)
    }
}