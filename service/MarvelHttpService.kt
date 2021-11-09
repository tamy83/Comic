package com.yensontam.comic.service

import com.yensontam.comic.model.ComicDataWrapper
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelHttpService {

  @GET("/v1/public/comics/{id}")
  fun getComic(@Path("id") id: Int,
               @Query("ts") timeStamp: String,
               @Query("apikey") apiKey: String,
               @Query("hash") hash: String): Call<ComicDataWrapper>
}