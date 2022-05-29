package com.example.samplegithubrepository.presentation.composable_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource

import com.example.samplegithubrepository.R


@Composable
fun DetailTopAppBar(title:String, onClick: ()-> Unit){
    TopAppBar(
        title = {
                Text(text = title, color = colorResource(R.color.white))
        },
        backgroundColor = colorResource(R.color.dark_blue),
        navigationIcon = {
            IconButton(onClick =  onClick ) {
                Image(
                    painterResource(R.drawable.ic_vector_backarrow_white),
                    contentDescription = "back button"

                )
            }
        }
    )
}