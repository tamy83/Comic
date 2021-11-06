package com.yensontam.comic.screens.comic

import com.yensontam.comic.screens.comic.model.ComicViewItem
import com.yensontam.comic.Config
import com.yensontam.comic.mvi.Data

class ComicActivityInteractorImpl(config: Config) : ComicActivityInteractor {

  override suspend fun retrieveComic(id: String): Data<ComicViewItem> {
    TODO("Not yet implemented")
  }
}