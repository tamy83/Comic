package com.yensontam.comic

import android.app.Application
import com.yensontam.comic.di.comicActivityViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ComicApp : Application() {
  override fun onCreate() {
    super.onCreate()

    setUpKoin()
  }

  private fun setUpKoin() {
    startKoin {
      androidContext(this@ComicApp)
      modules(
        listOf(
          comicActivityViewModelModule
        )
      )
    }
  }
}