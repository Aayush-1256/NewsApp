package com.example.appnews

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : ParentRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val parentRecycler : RecyclerView = findViewById(R.id.parent)
        parentRecycler.layoutManager =  LinearLayoutManager(this)
        val list = fetch()
        adapter = ParentRecycler()
        parentRecycler.adapter = adapter

    }

    fun fetch() : ArrayList<ArrayList<News>>
    {
       val queue = Volley.newRequestQueue(this)

        val category = listOf("business","entertainment","general","health","science","sports","technology")

        val l = ArrayList<ArrayList<News>>()

        for(i in 0 until category.size) {

            val url =
                "https://newsapi.org/v2/top-headlines?country=in&category=${category.get(i)}&apiKey=01cc1712ba3747548a9463796bcc0e33"
            print(url)
            val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {

                val list = ArrayList<News>()
                val newsJsonArray = it.getJSONArray("articles")

                for (j in 0 until newsJsonArray.length()) {
                    val json = newsJsonArray.getJSONObject(j)
                    val news = News(category.get(i).uppercase(), json.getString("title"), json.getString("urlToImage"),json.getString("content"))
                    list.add(news)
                }
//                Log.d("List", list.toString())
                l.add(ArrayList(list))
                adapter.update(l)
            },
                Response.ErrorListener {
                    print(it.localizedMessage)
                }
            ) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["User-Agent"] = "Mozilla/5.0"
                    return params
                }
//
            }
//
            queue.add(jsonObjectRequest)
        }

return l
}

    fun clicked(view: View) {
        val intent = Intent(this,Search::class.java)
        val search  = findViewById<TextView>(R.id.autoCompleteTextView).editableText.toString()
        intent.putExtra("search",search)
        startActivity(intent)
    }

}