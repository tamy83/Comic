package com.yensontam.comic.di

import com.yensontam.comic.screens.comic.ComicActivityViewModel
import com.yensontam.comic.screens.comic.ComicActivityInteractorImpl
import com.yensontam.comic.Config
import com.yensontam.comic.service.MarvelHttpApiImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val comicActivityViewModelModule = module {
  viewModel {
    ComicActivityViewModel(
      interactor = ComicActivityInteractorImpl(Config, MarvelHttpApiImpl()))
  }
}