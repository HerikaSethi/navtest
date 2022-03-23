package com.example.navtest

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Step1: Interface created to tell retrofit which methods we want to implement

//GET https://newsapi.org/v2/top-headlines/sources?apiKey=API_KEY
const val BASE_URL = "https://newsapi.org/"
const val API_KEY ="2ed5101c94ca43c18b17c2db6e15b5b5"

interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country: String, @Query("page")page: Int):Call<News>

    //https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1
}

//Retrofit object made (always made singleton so that only this object is called )
object NewsService {
    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //Now to tell retrofit that above is our interface for which we need implementation
        newsInstance = retrofit.create(NewsInterface::class.java)
    }

}