package com.example.samplegithubrepository.presentation.composable_view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource

import com.example.samplegithubrepository.R


@Composable
fun HomeTopAppBar(text:String, onClick: ()-> Unit){
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = text, color = colorResource(R.color.dark_blue))

            }
        },
        backgroundColor = colorResource(R.color.white),

    )
}