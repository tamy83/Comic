package com.yensontam.comic.screens.comic

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.yensontam.comic.databinding.ActivityComicBinding
import com.yensontam.comic.screens.comic.state.ComicActivityState
import com.yensontam.comic.screens.comic.state.ComicActivityViewEffect
import org.koin.android.ext.android.inject

class ComicActivity : AppCompatActivity() {

  private lateinit var binding: ActivityComicBinding
  private lateinit var imageView: ImageView
  private lateinit var titleTextView: TextView
  private lateinit var descriptionTextView: TextView

  private val viewModel: ComicActivityViewModel by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityComicBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    imageView = binding.image
    titleTextView = binding.title
    descriptionTextView = binding.description

    viewModel.stateLiveData.observe(this, {
      renderState(it)
    })

    viewModel.effectLiveEvent.observe(this, {
      showViewEffect(it)
    })
  }

  private fun renderState(state: ComicActivityState) {
    Glide.with(this).load(state.imageUrl).into(imageView)
    titleTextView.text = state.title
    descriptionTextView.text = state.description
  }

  private fun showViewEffect(viewEffect: ComicActivityViewEffect) {

  }

  companion object {

    val EXTRA_COMIC_ID = "extra_comic_id"
    @JvmStatic
    fun getStartIntent(context: Context, id: String): Intent {
      return Intent(context, ComicActivity::class.java).apply {
        putExtra(EXTRA_COMIC_ID, id)
      }
    }
  }
}
