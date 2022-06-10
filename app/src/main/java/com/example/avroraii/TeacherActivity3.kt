package com.example.avroraii

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


class TeacherActivity3 : AppCompatActivity()
{
    var questionsN : Int = 0
    var mode : Int = 0
    var dir : String = ""
    var single : View? = null
    var multi : View? = null
    var short : View? = null
    var questions : TextInputLayout? = null
    var questionsText : TextInputEditText? = null
    var constLayout : ViewGroup? = null
    var bottomLeft : View? = null
    var arrSingle : Array<RadioButton>? = null
    var arrMulti : Array<CheckBox>? = null
    var arrShort : Array<EditText>? = null
    var arrAnswers : Array<TextInputLayout>? = null
    var answersN : TextInputEditText? = null
    var arrAnswersText : Array<TextInputEditText>? = null
    var course : String = ""
    var testName : String = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher3)
        supportActionBar!!.title = "Создание теста"
        var sharedPreferences = getSharedPreferences("key", MODE_PRIVATE)
        single = findViewById(R.id.choiceSingle)
        multi = findViewById(R.id.choiceMulti)
        short = findViewById(R.id.choiceShort)
        questions = findViewById(R.id.Answers)
        questionsText = findViewById(R.id.questionText)
        answersN = findViewById(R.id.answers)
        constLayout = single!!.parent as ViewGroup
        questionsN = sharedPreferences.getInt("questionsN", -1) - 1
        course = sharedPreferences.getString("curCourse", "no").toString()
        testName = sharedPreferences.getString("title", "no").toString()
        var editor = sharedPreferences.edit()
        editor.putInt("questionsN", questionsN)
        editor.apply()
        dir = getExternalFilesDir(null).toString()
        var bw1 = BufferedWriter(FileWriter("$dir/$course$testName.txt", true))
        bw1.newLine()
        bw1.close()
    }

    override fun onBackPressed() {}

    fun onSingle(view : View)
    {
        (view as RadioButton).isChecked = false
        var answersN = findViewById<TextInputEditText>(R.id.answers)
        if (answersN.text.toString() == "")
        {
            val toast = Toast.makeText(applicationContext, "Недопустимое количество ответов.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (answersN.text.toString()[0] == '0')
        {
            val toast = Toast.makeText(applicationContext, "Недопустимое количество ответов.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        constLayout!!.removeView(single)
        constLayout!!.removeView(multi)
        constLayout!!.removeView(short)
        constLayout!!.removeView(questions)
        mode = 1
        var answersQ = Integer.parseInt(answersN!!.text.toString())
        arrSingle = Array<RadioButton>(answersQ, {RadioButton(this)})
        arrAnswers = Array<TextInputLayout>(answersQ, {TextInputLayout(this)})
        arrAnswersText = Array<TextInputEditText>(answersQ, {TextInputEditText(this)})
        --answersQ
        var layout = findViewById<ConstraintLayout>(R.id.constLayout)
        var set = ConstraintSet()
        var set1 = ConstraintSet()
        arrAnswers!![0].setBackgroundColor(4293961300.toInt())
        arrAnswers!![0].id = View.generateViewId()
        arrAnswersText!![0].id = View.generateViewId()
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

    fun onMulti(view : View)
    {
        (view as CheckBox).isChecked = false
        var answersN = findViewById<TextInputEditText>(R.id.answers)
        if (answersN.text.toString() == "")
        {
            val toast = Toast.makeText(applicationContext, "Недопустимое количество ответов.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (answersN.text.toString()[0] == '0')
        {
            val toast = Toast.makeText(applicationContext, "Недопустимое количество ответов.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        constLayout!!.removeView(single)
        constLayout!!.removeView(multi)
        constLayout!!.removeView(short)
        constLayout!!.removeView(questions)
        mode = 2
        var answersQ = Integer.parseInt(answersN!!.text.toString())
        arrMulti = Array<CheckBox>(answersQ, {CheckBox(this)})
        arrAnswers = Array<TextInputLayout>(answersQ, {TextInputLayout(this)})
        arrAnswersText = Array<TextInputEditText>(answersQ, {TextInputEditText(this)})
        --answersQ
        var layout = findViewById<ConstraintLayout>(R.id.constLayout)
        var set = ConstraintSet()
        var set1 = ConstraintSet()
        arrAnswers!![0].setBackgroundColor(4293961300.toInt())
        arrAnswers!![0].id = View.generateViewId()
        arrAnswersText!![0].id = View.generateViewId()
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
    }

    fun onShort(view : View)
    {
        var answersN = findViewById<TextInputEditText>(R.id.answers)
        if (answersN.text.toString() == "")
        {
            val toast = Toast.makeText(applicationContext, "Недопустимое количество ответов.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (answersN.text.toString()[0] == '0')
        {
            val toast = Toast.makeText(applicationContext, "Недопустимое количество ответов.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        constLayout!!.removeView(single)
        constLayout!!.removeView(multi)
        constLayout!!.removeView(short)
        constLayout!!.removeView(questions)
        mode = 3
        var answersQ = Integer.parseInt(answersN!!.text.toString())
        arrMulti = Array<CheckBox>(answersQ, {CheckBox(this)})
        arrAnswers = Array<TextInputLayout>(answersQ, {TextInputLayout(this)})
        arrAnswersText = Array<TextInputEditText>(answersQ, {TextInputEditText(this)})
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
            arrMulti!![i].highlightColor = 0xFF9800
            arrMulti!![i].setBackgroundColor(0xFF9800)
            arrMulti!![i].setButtonDrawable(R.drawable.empty)
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
    }

    fun onNext(view : View)
    {
        if (mode == 0)
        {
            val toast = Toast.makeText(applicationContext, "Выберите тип вопроса.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (mode == 1)
        {
            if (questionsText!!.text.toString() == "")
            {
                val toast = Toast.makeText(applicationContext, "Введите текст вопроса.", Toast.LENGTH_SHORT)
                toast.show()
                return
            }
            for (i in arrAnswersText!!)
            {
                if (i.text.toString() == "")
                {
                    val toast = Toast.makeText(applicationContext, "Заполните ответы.", Toast.LENGTH_SHORT)
                    toast.show()
                    return
                }
            }
            for (i in arrSingle!!)
            {
                if (i.isChecked)
                {
                    var bw1 = BufferedWriter(FileWriter("$dir/$course$testName.txt", true))
                    bw1.write(questionsText!!.text.toString())
                    bw1.newLine()
                    bw1.write("r")
                    bw1.newLine()
                    bw1.write(answersN!!.text.toString())
                    bw1.newLine()
                    for (j in arrAnswersText!!)
                    {
                        bw1.write(j.text.toString())
                        bw1.newLine()
                    }
                    for (j in 0..arrSingle!!.size)
                        if (i == arrSingle!![j])
                        {
                            bw1.write("$j")
                            break
                        }
                    bw1.close()
                    if (questionsN == 0)
                    {
                        var bw2 = BufferedWriter(FileWriter("$dir/$course.txt", true))
                        if (File("$dir/$course.txt").length() > 0)
                        {
                            bw2.newLine()
                            bw2.write(testName)
                        }
                        else
                            bw2.write(testName)
                        bw2.close()
                        intent = Intent(this, TeacherActivity1::class.java)
                        val toast = Toast.makeText(applicationContext, "Тест создан.", Toast.LENGTH_SHORT)
                        toast.show()
                        startActivity(intent)
                        return
                    }
                    intent = Intent(this, TeacherActivity3::class.java)
                    startActivity(intent)
                    return
                }
            }
            val toast = Toast.makeText(applicationContext, "Выберите верный ответ.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (mode == 2)
        {
            if (questionsText!!.text.toString() == "")
            {
                val toast = Toast.makeText(applicationContext, "Введите текст вопроса.", Toast.LENGTH_SHORT)
                toast.show()
                return
            }
            for (i in arrAnswersText!!)
            {
                if (i.text.toString() == "")
                {
                    val toast = Toast.makeText(applicationContext, "Заполните ответы.", Toast.LENGTH_SHORT)
                    toast.show()
                    return
                }
            }
            for (i in arrMulti!!)
            {
                if (i.isChecked)
                {
                    var bw1 = BufferedWriter(FileWriter("$dir/$course$testName.txt", true))
                    bw1.write(questionsText!!.text.toString())
                    bw1.newLine()
                    bw1.write("c")
                    bw1.newLine()
                    bw1.write(answersN!!.text.toString())
                    bw1.newLine()
                    for (j in arrAnswersText!!)
                    {
                        bw1.write(j.text.toString())
                        bw1.newLine()
                    }
                    var counter = 0
                    for (j in 0..(arrMulti!!.size - 1))
                        if (arrMulti!![j].isChecked)
                            ++counter
                    bw1.write("$counter")
                    for (j in 0..(arrMulti!!.size - 1))
                        if (arrMulti!![j].isChecked)
                        {
                            bw1.newLine()
                            bw1.write("$j")
                        }
                    bw1.close()
                    if (questionsN == 0)
                    {
                        var bw2 = BufferedWriter(FileWriter("$dir/$course.txt", true))
                        if (File("$dir/$course.txt").length() > 0)
                        {
                            bw2.newLine()
                            bw2.write(testName)
                        }
                        else
                            bw2.write(testName)
                        bw2.close()
                        intent = Intent(this, TeacherActivity1::class.java)
                        val toast = Toast.makeText(applicationContext, "Тест создан.", Toast.LENGTH_SHORT)
                        toast.show()
                        startActivity(intent)
                        return
                    }
                    intent = Intent(this, TeacherActivity3::class.java)
                    startActivity(intent)
                    return
                }
            }
            val toast = Toast.makeText(applicationContext, "Выберите верный ответ.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        if (mode == 3)
        {
            if (questionsText!!.text.toString() == "")
            {
                val toast = Toast.makeText(applicationContext, "Введите текст вопроса.", Toast.LENGTH_SHORT)
                toast.show()
                return
            }
            for (i in arrAnswersText!!)
            {
                if (i.text.toString() == "")
                {
                    val toast = Toast.makeText(applicationContext, "Заполните ответы.", Toast.LENGTH_SHORT)
                    toast.show()
                    return
                }
            }
                    var bw1 = BufferedWriter(FileWriter("$dir/$course$testName.txt", true))
                    bw1.write(questionsText!!.text.toString())
                    bw1.newLine()
                    bw1.write("s")
                    bw1.newLine()
                    bw1.write(answersN!!.text.toString())
                    for (j in arrAnswersText!!)
                    {
                        bw1.newLine()
                        bw1.write(j.text.toString())
                    }
                    bw1.close()
                    if (questionsN == 0)
                    {
                        var bw2 = BufferedWriter(FileWriter("$dir/$course.txt", true))
                        if (File("$dir/$course.txt").length() > 0)
                        {
                            bw2.newLine()
                            bw2.write(testName)
                        }
                        else
                            bw2.write(testName)
                        bw2.close()
                        intent = Intent(this, TeacherActivity1::class.java)
                        val toast = Toast.makeText(applicationContext, "Тест создан.", Toast.LENGTH_SHORT)
                        toast.show()
                        startActivity(intent)
                        return
                    }
                    intent = Intent(this, TeacherActivity3::class.java)
                    startActivity(intent)
                    return
        }
    }
}