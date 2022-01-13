package com.example.appnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val list : ArrayList<News>) : RecyclerView.Adapter<NewsViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_news,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val current = list.get(position)
        holder.title.text = current.Title
        Glide.with(holder.itemView.context).load(current.ImageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
val title : TextView = itemView.findViewById(R.id.textView)
val imageView :ImageView = itemView.findViewById(R.id.image)
}