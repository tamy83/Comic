package com.yensontam.comic.service

import com.yensontam.comic.model.Comic
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

class MarvelHttpApiImpl : MarvelHttpApi {

  private val marvelHttpService: MarvelHttpService = Retrofit.Builder()
    .baseUrl("https://gateway.marvel.com:443")
    .client(OkHttpClient.Builder()
      .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
      .build())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(MarvelHttpService::class.java)

  override fun getComic(id: Int): Comic? {
    val timestamp = System.currentTimeMillis().toString()
    val param = timestamp + PRIVATE_KEY + PUBLIC_KEY
    val hash = md5(param)
    val comicDataWrapper = marvelHttpService.getComic(id = id,
      timeStamp = timestamp,
      apiKey = PUBLIC_KEY,
      hash = hash
    ).execute().body()
    return comicDataWrapper?.data?.results?.first()
  }

  private val PUBLIC_KEY = "hidden"
  private val PRIVATE_KEY = "hidden"

  fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
  }

}