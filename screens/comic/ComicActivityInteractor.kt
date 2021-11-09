package com.yensontam.comic.screens.comic

import com.yensontam.comic.screens.comic.model.ComicViewItem
import com.yensontam.comic.mvi.Data

interface ComicActivityInteractor {
  suspend fun retrieveComic(id: Int) : Data<ComicViewItem>
}