package com.github.pedrodimoura.pulselivecodechallenge.features.news.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.github.pedrodimoura.pulselivecodechallenge.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.navigationFragmentContainer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }

    override fun onBackPressed() {
        if (!navController.popBackStack())
            super.onBackPressed()
    }

}
