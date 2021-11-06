package com.yensontam.comic.screens.comic.state

import com.yensontam.comic.screens.comic.model.ComicViewItem
import com.yensontam.comic.mvi.IAction

sealed class ComicActivityAction : IAction {
  data class HasComicViewItem(val comicViewItem: ComicViewItem) : ComicActivityAction()
}