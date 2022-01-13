package com.example.appnews

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class Search : AppCompatActivity() {

    private lateinit var mAdapter: SearchRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val recycler = findViewById<RecyclerView>(R.id.searchRecycler)
        recycler.layoutManager = LinearLayoutManager(this)

        val search = (intent.getStringExtra("search").toString())

        fetch(search)

        mAdapter = SearchRecycler(this)

        recycler.adapter=mAdapter

    }

    fun fetch(value : String)
    {
        val queue = Volley.newRequestQueue(this)

        val url = "https://newsapi.org/v2/everything?q=$value&apiKey=01cc1712ba3747548a9463796bcc0e33"

        val list = ArrayList<News>()

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {
            val newsJsonArray = it.getJSONArray("articles")

            for (j in 0 until newsJsonArray.length()) {
                val json = newsJsonArray.getJSONObject(j)
                val news = News("null", json.getString("title"), json.getString("urlToImage"),json.getString("content"))
                list.add(news)
            }

            Log.println(Log.ERROR,"n", list.toString())
            mAdapter.update(list)

        },
            Response.ErrorListener {

            }
        )  {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "Mozilla/5.0"
                return params
            }
//
        }



        queue.add(jsonObjectRequest)
    }

    fun click(news: News, view: Context)
    {
        val intent  = Intent(view,Article::class.java)
        intent.putExtra("title",news.Title)
        intent.putExtra("imageUrl",news.ImageUrl)
        intent.putExtra("content",news.content)
        startActivity(intent)
    }

}
