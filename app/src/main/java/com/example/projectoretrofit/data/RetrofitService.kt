package com.example.projectoretrofit.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("images/search")
    suspend fun listOfCats(
        @Query("limit") limit: Int = 5,
    ): List<CatResponse>

    data class CatResponse(
        val id: String,
        val url: String,
        val width: Int,
        val height: Int
    )

}

object RetrofitSerivceFactory {
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}