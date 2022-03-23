package com.example.navtest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsAdapter(val context: Context, val articles:List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewholer>(){

    lateinit var database: NewsDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewholer {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewholer(view)
    }

    override fun onBindViewHolder(holder: ArticleViewholer, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)

        database = NewsDatabase.getDatabase(context)
        holder.newsDownload.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("Adapter", "onBindViewHolder: ")
                database.newsTableDao().insertNews(NewsTable(0, articles[position].author, articles[position].title, articles[position].description,articles[position].url))

//                database.newsTableDao().getNews().observe(this, Observer {
//                    Log.d("call", it.toString())
//                }
            }
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }


    class ArticleViewholer(itemView: View): RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)
        var newsDownload = itemView.findViewById<Button>(R.id.newsDownload)

    }
}


