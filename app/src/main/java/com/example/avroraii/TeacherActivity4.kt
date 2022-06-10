package com.example.avroraii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import java.io.BufferedWriter
import java.io.FileWriter

class TeacherActivity4 : AppCompatActivity()
{
    var clicked = false
    var dir = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher4)
        supportActionBar!!.title = "Отчёт по тесту"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var webView = findViewById<WebView>(R.id.webView)
        dir = getExternalFilesDir(null).toString()
        var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
        var testName = sharedPreferences.getString("title", "0")
        var course = sharedPreferences.getString("curCourse", "0")
        webView.settings.allowContentAccess = true
        webView.settings.allowFileAccess = true
        webView.loadUrl("file://$dir/$course$testName-stat.html")
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
}