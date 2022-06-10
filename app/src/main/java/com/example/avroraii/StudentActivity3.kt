package com.example.avroraii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class StudentActivity3 : AppCompatActivity()
{
    var dir : String = ""
    var questionsN = 0
    var answersQ = 0
    var constLayout : ViewGroup? = null
    var arrSingle : Array<RadioButton>? = null
    var arrMulti : Array<CheckBox>? = null
    var arrShort : Array<EditText>? = null
    var arrAnswers : Array<TextInputLayout>? = null
    var arrAnswersText : Array<TextInputEditText>? = null
    var questionsText : TextInputEditText? = null
    var mode = 0
    var score = 0
    var course = ""
    var testName = ""
    var correctNum = 0
    var correctStr = ""
    var last = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student3)
        supportActionBar!!.title = "Прохождение теста"
        dir = getExternalFilesDir(null).toString()
        var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
        last = sharedPreferences.getInt("lastLine", 0)
        course = sharedPreferences.getString("curCourse", "no").toString()
        testName = sharedPreferences.getString("title", "no").toString()
        questionsN = sharedPreferences.getInt("questionsN", 0) - 1
        score = sharedPreferences.getInt("score", 0)
        constLayout = findViewById<ViewGroup?>(R.id.constLayout)
        questionsText = findViewById(R.id.questionText)
        var reader = BufferedReader(FileReader("$dir/$course$testName.txt"))
        for (i in 0..last)
            reader.readLine()
        ++last
        questionsText!!.setText(reader.readLine())
        questionsText!!.isFocusable = false
        questionsText!!.isClickable = false
        ++last
        var type = reader.readLine()
        if (type == "r")
            mode = 1
        if (type == "c")
            mode = 2
        if (type == "s")
            mode = 3
        ++last
        answersQ = Integer.parseInt(reader.readLine())
        if (mode == 1)
        {
            arrSingle = Array<RadioButton>(answersQ, { RadioButton(this) })
            arrAnswers = Array<TextInputLayout>(answersQ, { TextInputLayout(this) })
            arrAnswersText = Array<TextInputEditText>(answersQ, { TextInputEditText(this) })
            --answersQ
            var layout = findViewById<ConstraintLayout>(R.id.constLayout)
            var set = ConstraintSet()
            var set1 = ConstraintSet()
            arrAnswers!![0].setBackgroundColor(4293961300.toInt())
            arrAnswers!![0].id = View.generateViewId()
            arrAnswersText!![0].id = View.generateViewId()
            ++last
            arrAnswersText!![0].setText(reader.readLine())
            arrAnswersText!![0].isClickable = false
            arrAnswersText!![0].isFocusable = false
            arrSingle!![0].highlightColor = 0xFF9800
            arrSingle!![0].setBackgroundColor(0xFF9800)
            arrSingle!![0].setButtonDrawable(R.drawable.radiooff)
            arrSingle!![0].id = View.generateViewId()
            arrSingle!![0].setOnClickListener{onRadio(arrSingle!![0])}
            layout.addView(arrSingle!![0], 0)
            layout.addView(arrAnswers!![0], 0)
            set.clone(layout)
            set.connect(arrSingle!![0].id, ConstraintSet.TOP, R.id.QuestionText, ConstraintSet.BOTTOM, 0)
            set.connect(arrSingle!![0].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 0)
            set1.clone(layout)
            set1.connect(arrAnswers!![0].id, ConstraintSet.TOP, R.id.QuestionText, ConstraintSet.BOTTOM, 0)
            set1.connect(arrAnswers!![0].id, ConstraintSet.START, arrSingle!![0].id, ConstraintSet.END, 5)
            set.applyTo(layout)
            set1.applyTo(layout)
            set.clone(layout)
            set.connect(arrSingle!![0].id, ConstraintSet.TOP, arrAnswers!![0].id, ConstraintSet.TOP, 0)
            set.connect(arrSingle!![0].id, ConstraintSet.BOTTOM, arrAnswers!![0].id, ConstraintSet.BOTTOM, 0)
            set.connect(arrSingle!![0].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 5)
            set.applyTo(layout)
            arrAnswers!![0].addView(arrAnswersText!![0])
            arrAnswersText!![0].setTextColor(0xFFFFFFFF.toInt())
            arrAnswersText!![0].setBackgroundColor(0xFFFF9800.toInt())
            for (i in 1..answersQ)
            {
                arrAnswers!![i].setBackgroundColor(4293961300.toInt())
                arrAnswers!![i].id = View.generateViewId()
                arrAnswersText!![i].id = View.generateViewId()
                ++last
                arrAnswersText!![i].setText(reader.readLine())
                arrAnswersText!![i].isClickable = false
                arrAnswersText!![i].isFocusable = false
                arrSingle!![i].highlightColor = 0xFF9800
                arrSingle!![i].setBackgroundColor(0xFF9800)
                arrSingle!![i].setButtonDrawable(R.drawable.radiooff)
                arrSingle!![i].id = View.generateViewId()
                arrSingle!![i].setOnClickListener{onRadio(arrSingle!![i])}
                layout.addView(arrSingle!![i], 0)
                layout.addView(arrAnswers!![i], 0)
                set.clone(layout)
                set.connect(arrSingle!![i].id, ConstraintSet.TOP, R.id.QuestionText, ConstraintSet.BOTTOM, 0)
                set.connect(arrSingle!![i].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 0)
                set1.clone(layout)
                set1.connect(arrAnswers!![i].id, ConstraintSet.TOP, arrAnswers!![i - 1].id, ConstraintSet.BOTTOM, 0)
                set1.connect(arrAnswers!![i].id, ConstraintSet.START, arrSingle!![i].id, ConstraintSet.END, 5)
                set.applyTo(layout)
                set1.applyTo(layout)
                set.clone(layout)
                set.connect(arrSingle!![i].id, ConstraintSet.TOP, arrAnswers!![i].id, ConstraintSet.TOP, 0)
                set.connect(arrSingle!![i].id, ConstraintSet.BOTTOM, arrAnswers!![i].id, ConstraintSet.BOTTOM, 0)
                set.connect(arrSingle!![i].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 5)
                set.applyTo(layout)
                arrAnswers!![i].addView(arrAnswersText!![i])
                arrAnswersText!![i].setTextColor(0xFFFFFFFF.toInt())
                arrAnswersText!![i].setBackgroundColor(0xFFFF9800.toInt())
            }
            ++last
            correctNum = Integer.parseInt(reader.readLine())
            reader.close()
            var editor = sharedPreferences.edit()
            editor.putInt("questionsN", questionsN)
            editor.putInt("lastLine", last)
            editor.apply()
        }
        if (mode == 2)
        {
            arrMulti = Array<CheckBox>(answersQ, { CheckBox(this) })
            arrAnswers = Array<TextInputLayout>(answersQ, { TextInputLayout(this) })
            arrAnswersText = Array<TextInputEditText>(answersQ, { TextInputEditText(this) })
            --answersQ
            var layout = findViewById<ConstraintLayout>(R.id.constLayout)
            var set = ConstraintSet()
            var set1 = ConstraintSet()
            arrAnswers!![0].setBackgroundColor(4293961300.toInt())
            arrAnswers!![0].id = View.generateViewId()
            arrAnswersText!![0].id = View.generateViewId()
            ++last
            arrAnswersText!![0].setText(reader.readLine())
            arrAnswersText!![0].isClickable = false
            arrAnswersText!![0].isFocusable = false
            arrMulti!![0].highlightColor = 0xFF9800
            arrMulti!![0].setBackgroundColor(0xFF9800)
            arrMulti!![0].setButtonDrawable(R.drawable.checkoff)
            arrMulti!![0].id = View.generateViewId()
            arrMulti!![0].setOnClickListener{onCheck(arrMulti!![0])}
            layout.addView(arrMulti!![0], 0)
            layout.addView(arrAnswers!![0], 0)
            set.clone(layout)
            set.connect(arrMulti!![0].id, ConstraintSet.TOP, R.id.QuestionText, ConstraintSet.BOTTOM, 0)
            set.connect(arrMulti!![0].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 0)
            set1.clone(layout)
            set1.connect(arrAnswers!![0].id, ConstraintSet.TOP, R.id.QuestionText, ConstraintSet.BOTTOM, 0)
            set1.connect(arrAnswers!![0].id, ConstraintSet.START, arrMulti!![0].id, ConstraintSet.END, 5)
            set.applyTo(layout)
            set1.applyTo(layout)
            set.clone(layout)
            set.connect(arrMulti!![0].id, ConstraintSet.TOP, arrAnswers!![0].id, ConstraintSet.TOP, 0)
            set.connect(arrMulti!![0].id, ConstraintSet.BOTTOM, arrAnswers!![0].id, ConstraintSet.BOTTOM, 0)
            set.connect(arrMulti!![0].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 5)
            set.applyTo(layout)
            arrAnswers!![0].addView(arrAnswersText!![0])
            arrAnswersText!![0].setTextColor(0xFFFFFFFF.toInt())
            arrAnswersText!![0].setBackgroundColor(0xFFFF9800.toInt())
            for (i in 1..answersQ)
            {
                arrAnswers!![i].setBackgroundColor(4293961300.toInt())
                arrAnswers!![i].id = View.generateViewId()
                arrAnswersText!![i].id = View.generateViewId()
                ++last
                arrAnswersText!![i].setText(reader.readLine())
                arrAnswersText!![i].isClickable = false
                arrAnswersText!![i].isFocusable = false
                arrMulti!![i].highlightColor = 0xFF9800
                arrMulti!![i].setBackgroundColor(0xFF9800)
                arrMulti!![i].setButtonDrawable(R.drawable.checkoff)
                arrMulti!![i].id = View.generateViewId()
                arrMulti!![i].setOnClickListener{onCheck(arrMulti!![i])}
                layout.addView(arrMulti!![i], 0)
                layout.addView(arrAnswers!![i], 0)
                set.clone(layout)
                set.connect(arrMulti!![i].id, ConstraintSet.TOP, R.id.QuestionText, ConstraintSet.BOTTOM, 0)
                set.connect(arrMulti!![i].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 0)
                set1.clone(layout)
                set1.connect(arrAnswers!![i].id, ConstraintSet.TOP, arrAnswers!![i - 1].id, ConstraintSet.BOTTOM, 0)
                set1.connect(arrAnswers!![i].id, ConstraintSet.START, arrMulti!![i].id, ConstraintSet.END, 5)
                set.applyTo(layout)
                set1.applyTo(layout)
                set.clone(layout)
                set.connect(arrMulti!![i].id, ConstraintSet.TOP, arrAnswers!![i].id, ConstraintSet.TOP, 0)
                set.connect(arrMulti!![i].id, ConstraintSet.BOTTOM, arrAnswers!![i].id, ConstraintSet.BOTTOM, 0)
                set.connect(arrMulti!![i].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 5)
                set.applyTo(layout)
                arrAnswers!![i].addView(arrAnswersText!![i])
                arrAnswersText!![i].setTextColor(0xFFFFFFFF.toInt())
                arrAnswersText!![i].setBackgroundColor(0xFFFF9800.toInt())
            }
            reader.close()
            var editor = sharedPreferences.edit()
            editor.putInt("questionsN", questionsN)
            editor.putInt("lastLine", last)
            editor.apply()
        }
        if (mode == 3)
        {
            arrMulti = Array<CheckBox>(1, { CheckBox(this) })
            arrAnswers = Array<TextInputLayout>(1, { TextInputLayout(this) })
            arrAnswersText = Array<TextInputEditText>(1, { TextInputEditText(this) })
            --answersQ
            var layout = findViewById<ConstraintLayout>(R.id.constLayout)
            var set = ConstraintSet()
            var set1 = ConstraintSet()
            arrAnswers!![0].setBackgroundColor(4293961300.toInt())
            arrAnswers!![0].id = View.generateViewId()
            arrAnswersText!![0].id = View.generateViewId()
            arrMulti!![0].highlightColor = 0xFF9800
            arrMulti!![0].setBackgroundColor(0xFF9800)
            arrMulti!![0].setButtonDrawable(R.drawable.empty)
            arrMulti!![0].id = View.generateViewId()
            arrMulti!![0].setOnClickListener{onCheck(arrMulti!![0])}
            arrMulti!![0].isClickable = false
            arrMulti!![0].isFocusable = false
            layout.addView(arrMulti!![0], 0)
            layout.addView(arrAnswers!![0], 0)
            set.clone(layout)
            set.connect(arrMulti!![0].id, ConstraintSet.TOP, R.id.QuestionText, ConstraintSet.BOTTOM, 0)
            set.connect(arrMulti!![0].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 0)
            set1.clone(layout)
            set1.connect(arrAnswers!![0].id, ConstraintSet.TOP, R.id.QuestionText, ConstraintSet.BOTTOM, 0)
            set1.connect(arrAnswers!![0].id, ConstraintSet.START, arrMulti!![0].id, ConstraintSet.END, 5)
            set.applyTo(layout)
            set1.applyTo(layout)
            set.clone(layout)
            set.connect(arrMulti!![0].id, ConstraintSet.TOP, arrAnswers!![0].id, ConstraintSet.TOP, 0)
            set.connect(arrMulti!![0].id, ConstraintSet.BOTTOM, arrAnswers!![0].id, ConstraintSet.BOTTOM, 0)
            set.connect(arrMulti!![0].id, ConstraintSet.START, R.id.constLayout, ConstraintSet.START, 5)
            set.applyTo(layout)
            arrAnswers!![0].addView(arrAnswersText!![0])
            arrAnswersText!![0].setTextColor(0xFFFFFFFF.toInt())
            arrAnswersText!![0].setBackgroundColor(0xFFFF9800.toInt())
            reader.close()
            var editor = sharedPreferences.edit()
            editor.putInt("questionsN", questionsN)
            editor.putInt("lastLine", last)
            editor.apply()
        }
    }

    fun onRadio(clicked : RadioButton)
    {
        for (i in arrSingle!!)
            if (i == clicked)
            {
                i.isChecked = true
                i.setButtonDrawable(R.drawable.radioon)
            }
            else
            {
                i.isChecked = false
                i.setButtonDrawable(R.drawable.radiooff)
            }
    }

    fun onCheck(clicked : CheckBox)
    {
        for (i in arrMulti!!)
            if (i == clicked)
            {
                if (i.isChecked)
                    i.setButtonDrawable(R.drawable.checkon)
                else
                    i.setButtonDrawable(R.drawable.checkoff)
                break
            }
    }

    override fun onBackPressed() {}

    fun onNext(view : View)
    {
        if (mode == 1)
        {
                    if (arrSingle!![correctNum].isChecked)
                        ++score
                    var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
                    var editor = sharedPreferences.edit()
                    editor.putInt("score", score)
                    editor.apply()
            if (questionsN == 0)
            {
                intent = Intent(this, StudentActivity1::class.java)
                var reader = BufferedReader(FileReader("$dir/$course$testName.txt"))
                var total = reader.readLine()
                reader.close()
                var date = Date()
                var dt = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
                var writer = BufferedWriter(FileWriter("$dir/$course$testName-stat.html", true))
                var name = sharedPreferences.getString("name", "no")
                if (File("$dir/$course$testName-stat.html").length() > 0)
                    writer.newLine()
                writer.write("<div>${dt.format(date)} --- $name --- $score/$total</div>")
                writer.close()
                val toast = Toast.makeText(applicationContext, "Тест пройден. Результат: $score/$total", Toast.LENGTH_SHORT)
                toast.show()
                startActivity(intent)
                return
            }
            intent = Intent(this, StudentActivity3::class.java)
            startActivity(intent)
            return
        }
        if (mode == 2)
        {
                ++score
                var reader = BufferedReader(FileReader("$dir/$course$testName.txt"))
                for (i in 0..last)
                    reader.readLine()
                ++last
                var rights = Integer.parseInt(reader.readLine())
                var answerRight = BooleanArray(answersQ + 1, {false})
                for (i in 1..rights)
                {
                    ++last
                    answerRight[Integer.parseInt(reader.readLine())] = true
                }
                reader.close()
                for (i in 0..answersQ)
                {
                    if (answerRight[i] != arrMulti!![i].isChecked)
                    {
                        --score
                        break
                    }
                }
                var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
                var editor = sharedPreferences.edit()
                editor.putInt("lastLine", last)
                editor.putInt("score", score)
                editor.apply()
            if (questionsN == 0)
            {
                intent = Intent(this, StudentActivity1::class.java)
                var reader = BufferedReader(FileReader("$dir/$course$testName.txt"))
                var total = reader.readLine()
                reader.close()
                var date = Date()
                var dt = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
                var writer = BufferedWriter(FileWriter("$dir/$course$testName-stat.html", true))
                var name = sharedPreferences.getString("name", "no")
                if (File("$dir/$course$testName-stat.html").length() > 0)
                    writer.newLine()
                writer.write("<div>${dt.format(date)} --- $name --- $score/$total</div>")
                writer.close()
                val toast = Toast.makeText(applicationContext, "Тест пройден. Результат: $score/$total", Toast.LENGTH_SHORT)
                toast.show()
                startActivity(intent)
                return
            }
                intent = Intent(this, StudentActivity3::class.java)
                startActivity(intent)
                return
        }
        if (mode == 3)
        {
            var reader = BufferedReader(FileReader("$dir/$course$testName.txt"))
            for (i in 0..last)
                reader.readLine()
            var inputanswer = arrAnswersText!![0].text.toString()
            var line = ""
            for (i in 0..answersQ)
            {
                ++last
                line = reader.readLine()
                if (inputanswer == line)
                    ++score
            }
            var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
            var editor = sharedPreferences.edit()
            editor.putInt("lastLine", last)
            editor.putInt("score", score)
            editor.apply()
            if (questionsN == 0)
            {
                intent = Intent(this, StudentActivity1::class.java)
                var reader = BufferedReader(FileReader("$dir/$course$testName.txt"))
                var total = reader.readLine()
                reader.close()
                var date = Date()
                var dt = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
                var writer = BufferedWriter(FileWriter("$dir/$course$testName-stat.html", true))
                var name = sharedPreferences.getString("name", "no")
                if (File("$dir/$course$testName-stat.html").length() > 0)
                    writer.newLine()
                writer.write("<div>${dt.format(date)} --- $name --- $score/$total</div>")
                writer.close()
                val toast = Toast.makeText(applicationContext, "Тест пройден. Результат: $score/$total", Toast.LENGTH_SHORT)
                toast.show()
                startActivity(intent)
                return
            }
            intent = Intent(this, StudentActivity3::class.java)
            startActivity(intent)
            return
        }
    }
}