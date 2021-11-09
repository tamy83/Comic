package com.yensontam.comic.screens.comic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yensontam.comic.SingleLiveEvent
import com.yensontam.comic.screens.comic.ComicActivity.Companion.EXTRA_COMIC_ID
import com.yensontam.comic.mvi.Data
import com.yensontam.comic.screens.comic.state.ComicActivityAction
import com.yensontam.comic.screens.comic.state.ComicActivityIntent
import com.yensontam.comic.screens.comic.state.ComicActivityState
import com.yensontam.comic.screens.comic.state.ComicActivityViewEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComicActivityViewModel(val interactor: ComicActivityInteractor) : ViewModel() {

  var stateLiveData = MutableLiveData(ComicActivityState("", "", ""))
  var effectLiveEvent = SingleLiveEvent<ComicActivityViewEffect>()

  val currentState: ComicActivityState
    get() = stateLiveData.value ?: ComicActivityState("", "", "")

  fun onIntentReceived(intent: ComicActivityIntent) {
    when (intent) {
      is ComicActivityIntent.LoadedIntent -> {
        handledLoadedIntent(intent)
      }
    }
  }

  private fun handledLoadedIntent(intent: ComicActivityIntent.LoadedIntent) {
    val comicId = intent.androidIntent.getIntExtra(EXTRA_COMIC_ID, 123)

    if (comicId != -1) {
      viewModelScope.launch(Dispatchers.IO) {
        val result = interactor.retrieveComic(comicId)
        when (result) {
          is Data.Success -> {
            val comicViewItem = result.value
            setState(ComicActivityAction.HasComicViewItem(comicViewItem = comicViewItem))
          }
          is Data.Failure -> {
            // handle error
          }
        }
      }
    } else {
      // handle error
    }

  }

  private fun setState(action: ComicActivityAction) {
    stateLiveData.postValue(currentState.consumeAction(action))
  }

}