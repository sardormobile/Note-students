package com.example.mynote

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynote.Adapter.LessonAdapter
import com.example.mynote.model.LessonModell
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.editext_layout.*
import kotlinx.android.synthetic.main.editext_layout.view.*

class MainActivity : AppCompatActivity() {
    private val lesson_list = arrayListOf<LessonModell>()
    private lateinit var viewModel: MainViewModel
    private lateinit var lessonAdapter: LessonAdapter
    private lateinit var editext_id: TextInputEditText
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lessonAdapter = LessonAdapter(this, lesson_list, object: ItemAdapterListener{})

        lesson_recycler.layoutManager = LinearLayoutManager(this)
        lesson_recycler.adapter = lessonAdapter

        val builder = AlertDialog.Builder(this@MainActivity)
             with(builder){
                 setTitle("")
                 setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->

                     lesson_list.add(LessonModell(""))
                 })
                 setNegativeButton("no", DialogInterface.OnClickListener { dialog, which ->

            })
        }



        lesson_add_btn.setOnClickListener{

            val builder  = AlertDialog.Builder(this)
            val inflater = layoutInflater.inflate(R.layout.editext_layout, null)
            editext_id = inflater.ediText

            with(builder){

                setTitle("Fan Yaratish")

                setPositiveButton("Saqlash", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity, "Ok ${editext_id.text}", Toast.LENGTH_SHORT).show()

                    lesson_list.add(LessonModell("${editext_id.text}"))
                    lessonAdapter.notifyDataSetChanged()
                    Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show()

                })

                setNegativeButton("Bekor qilish", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity, "No", Toast.LENGTH_SHORT).show()

                    builder.setOnDismissListener { Toast.makeText(this@MainActivity, "dissmis", Toast.LENGTH_SHORT).show() }
                })

                setView(inflater)
                show()
            }
        }


    }
}