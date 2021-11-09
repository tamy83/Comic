package com.yensontam.comic.screens.comic

import com.yensontam.comic.screens.comic.model.ComicViewItem
import com.yensontam.comic.Config
import com.yensontam.comic.model.Comic
import com.yensontam.comic.mvi.Data
import com.yensontam.comic.service.MarvelHttpApi

class ComicActivityInteractorImpl(private val config: Config, private val marvelHttpApi: MarvelHttpApi) : ComicActivityInteractor {

  override suspend fun retrieveComic(id: Int): Data<ComicViewItem> {
    val comic = marvelHttpApi.getComic(id)
    comic?.let {
      return Data.Success(comicToComicViewItem(it))
    } ?: return Data.Failure(Throwable())
  }

  private fun comicToComicViewItem(comic: Comic) : ComicViewItem {
    return ComicViewItem(imageUrl = createThumbnailUrl(comic.thumbnail.path, comic.thumbnail.extension),
      title = comic.title,
      description = comic.description)
  }

  private fun createThumbnailUrl(path: String, extension: String, variant: String = "portrait_incredible") : String {
    return "$path/$variant.$extension"
  }

}