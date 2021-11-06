package com.yensontam.comic.screens.comic.state

import com.yensontam.comic.mvi.IAction
import com.yensontam.comic.mvi.IState

data class ComicActivityState(
  var imageUrl: String,
  var title: String,
  var description: String
) : IState {

  override fun consumeAction(action: IAction): ComicActivityState {
    val newState = this.copy()

    when (action) {
      is ComicActivityAction.HasComicViewItem -> {
        newState.imageUrl = action.comicViewItem.imageUrl
        newState.title = action.comicViewItem.title
        newState.description = action.comicViewItem.description
      }
    }

    return newState
  }
}