package com.example.appnews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SearchRecycler(val search : Search) : RecyclerView.Adapter<searchHolder>() {

    private val list = ArrayList<News>()
    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_content,parent,false)
        val viewHolder = searchHolder(view)
        mContext = parent.context
        view.setOnClickListener {
            search.click(list.get(viewHolder.adapterPosition),mContext)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: searchHolder, position: Int) {
      val current = list.get(position)
        holder.title.text = current.Title
        Glide.with(holder.itemView.context).load(current.ImageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(l : ArrayList<News>)
    {
        list.clear()
        list.addAll(l)
        notifyDataSetChanged()
    }


}

class searchHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val title = itemView.findViewById<TextView>(R.id.textView2)
    val image = itemView.findViewById<ImageView>(R.id.image)
}