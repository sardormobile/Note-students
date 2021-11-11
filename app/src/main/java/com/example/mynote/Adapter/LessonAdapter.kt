package com.example.mynote.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.R
import com.example.mynote.SaveActivity
import com.example.mynote.model.LessonModell
import com.example.mynote.model.SaveModel
import kotlinx.android.synthetic.main.lesson_item_layout.view.*


class LessonAdapter(val items: Array<LessonModell>): RecyclerView.Adapter<LessonAdapter.ItemHolder>() {
    inner class ItemHolder(v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.lesson_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       val item = items[position]
        holder.itemView.lesson_name_id.text = item.lessonName
        holder.itemView.leson_click_id.setOnClickListener {
//            val intent = Intent(holder.itemView.context, SaveActivity::class.java)
//            intent.putExtra("_key",item)
            holder.itemView.context.startActivity(Intent(holder.itemView.context, SaveActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}