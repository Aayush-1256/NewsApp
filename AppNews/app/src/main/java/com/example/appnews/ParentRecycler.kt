package com.example.appnews

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ParentRecycler() : RecyclerView.Adapter<RecyclerHolder>()
{
    private val list : ArrayList<ArrayList<News>> = ArrayList<ArrayList<News>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent,parent,false)
        return RecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val current = list.get(position)
        holder.textView.text = current[0].Category
        val newsAdapter = NewsAdapter(current)
        holder.recyclerView.layoutManager = LinearLayoutManager(holder.recyclerView.context,RecyclerView.HORIZONTAL,false)
        holder.recyclerView.adapter = newsAdapter
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(l : ArrayList<ArrayList<News>>)
    {
        list.clear()
        list.addAll(l)
        notifyDataSetChanged()
    }

}

class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val textView : TextView  = itemView.findViewById(R.id.text)
    val recyclerView : RecyclerView = itemView.findViewById(R.id.recycler)
}