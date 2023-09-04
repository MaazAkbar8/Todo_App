package com.example.todo_app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todo_app.DataClass.TodoData
import com.example.todo_app.R

class myAdapter(private val imageuri: List<TodoData>):
    RecyclerView.Adapter<myAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       private val img:ImageView=itemView.findViewById(R.id.img)
        fun bind(imageItem: TodoData) {


            // Load image using Glide
            Glide.with(itemView.context)
                .load(imageItem.image)
                .into(img)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layoutimage, parent, false)
        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return imageuri.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item =imageuri [position]// usersList
        // holder.name.append("${item.name}")
        holder.bind(item)

    }
}