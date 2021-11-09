package com.yensontam.comic.model

data class ComicDataWrapper(
  val code: Int,
  val status: String,
  val data: ComicDataContainer
)

data class ComicDataContainer(
  val results: Array<Comic>
)

data class Comic(
  val copyright: String,
  val title: String,
  val description: String,
  val thumbnail: Thumbnail
)

class Thumbnail (
  var path: String,
  var extension: String
)