package com.yensontam.comic.screens.comic.state

import android.content.Intent
import com.yensontam.comic.mvi.IIntent

sealed class ComicActivityIntent : IIntent {
  data class LoadedIntent(val androidIntent: Intent) : ComicActivityIntent()
}