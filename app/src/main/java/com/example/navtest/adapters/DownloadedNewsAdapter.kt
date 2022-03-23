package com.example.navtest

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class DownloadedNewsAdapter(val context: Context):RecyclerView.Adapter<DownloadedNewsAdapter.DArticleViewHolder>(){

    var users: List<NewsTable> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setDataItems(dataList: List<NewsTable>) {
        users = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DArticleViewHolder {
      val view = LayoutInflater.from(context).inflate(R.layout.downloaded_item_layout,parent,false)
      return DArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: DArticleViewHolder, position: Int) {
        val user = users[position]
        holder.downloadedNewsTitle.text = user.title
        holder.downloadedNewsDescription.text = user.description
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class DArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var downloadedNewsTitle = itemView.findViewById<TextView>(R.id.downloadedNewsTitle)
        var downloadedNewsDescription = itemView.findViewById<TextView>(R.id.downloadedNewsDescription)

    }
}