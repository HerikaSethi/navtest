package com.example.navtest


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newsTable")
data class NewsTable(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val author: String?,
    val title:String,
    val description:String,
    val url:String,
    //val urlToImage:String
)

