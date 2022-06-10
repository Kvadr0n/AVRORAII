package com.example.avroraii

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedWriter
import java.io.FileWriter

class StudentActivity1 : AppCompatActivity()
{
    var coursesN : Int? = null
    var clicked = false
    var dir : String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student1)
        supportActionBar!!.title = "Режим Учащегося"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        dir = getExternalFilesDir(null).toString()
        var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
        coursesN = sharedPreferences.getInt("coursesN", 0)
        var string = "$coursesN"
        for (i in 0..coursesN!!)
            createButton(sharedPreferences.getString("courses$i", "no"))
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

    override fun onBackPressed() {}

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
        var editor = sharedPreferences.edit()
        editor.putString("curCourse", button.text.toString())
        editor.apply();
        intent = Intent(this, StudentActivity2::class.java)
        startActivity(intent)
    }
}