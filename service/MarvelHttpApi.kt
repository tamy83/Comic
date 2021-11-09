package com.yensontam.comic.service

import com.yensontam.comic.model.Comic

interface MarvelHttpApi {
  fun getComic(id: Int): Comic?
}