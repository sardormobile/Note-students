package com.example.mynote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynote.Adapter.LessonAdapter
import com.example.mynote.model.LessonModell
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val lesson_list = arrayOf(
        LessonModell("Fizika"),
        LessonModell("Matem"),
        LessonModell("MKG"),
        LessonModell("Chizma"),
        LessonModell("Antrapologiya"),
        LessonModell("Fizika Labaratorya"),
        LessonModell("MSSA"),
        LessonModell("MKG Labaratoriya"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lesson_recycler.layoutManager = LinearLayoutManager(this)
        lesson_recycler.adapter = LessonAdapter(lesson_list)

        add_btn.setOnClickListener{
            startActivity(Intent(this, SaveActivity::class.java))
        }

    }
}