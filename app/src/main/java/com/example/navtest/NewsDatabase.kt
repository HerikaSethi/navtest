package com.example.navtest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NewsTable::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract  fun newsTableDao(): NewsTableDAO

    //companion object will be visible to other classes
    companion object{

        //singleton class: newsDatabase object will be created only once
        @Volatile
        //rights are immediately visible to other threads
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}