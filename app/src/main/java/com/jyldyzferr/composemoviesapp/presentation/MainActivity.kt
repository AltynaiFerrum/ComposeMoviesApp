package com.jyldyzferr.composemoviesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jyldyzferr.composemoviesapp.MyMoviesApp
import com.jyldyzferr.composemoviesapp.presentation.screens.home_screen.MoviesViewModel
import com.jyldyzferr.composemoviesapp.presentation.theme.ComposeMoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MoviesViewModel = viewModel(

            )
            ComposeMoviesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyMoviesApp()
                }
            }
        }
    }
}


