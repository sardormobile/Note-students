package com.example.mynote.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.MainViewModel
import com.example.mynote.R
import com.example.mynote.model.SaveModel
import com.example.mynote.model.SaveTextModel
import kotlinx.android.synthetic.main.save_item_layout.view.*
import kotlin.reflect.typeOf

class SaveAdapter(val items: ArrayList<SaveModel>, val items_text: ArrayList<SaveTextModel>): RecyclerView.Adapter<SaveAdapter.ItemHolder>() {
    inner class ItemHolder(v: View): RecyclerView.ViewHolder(v)
    lateinit var viewModel: MainViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.save_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.save_data_id.text = item.data
        holder.itemView.save_image_id.setImageBitmap(item.image)
        holder.itemView.save_time_id.text = item.time

        val item_text = items_text[position]
        holder.itemView.save_text_id.text = item_text.text


    //        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}