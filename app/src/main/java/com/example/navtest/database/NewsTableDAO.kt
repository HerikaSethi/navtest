package com.example.navtest

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsTableDAO {
    @Insert
    suspend fun insertNews (newsTable: NewsTable)

    @Update
    suspend fun updateNews (newsTable: NewsTable)

    @Delete
    suspend fun deleteNews (newsTable: NewsTable)

    @Query("SELECT * FROM newsTable")
    fun getNews(): LiveData<List<NewsTable>>

    //-------------------------//
    @Query("SELECT * FROM newsTable")
    fun getAllNews(): LiveData<List<NewsTable>>
}