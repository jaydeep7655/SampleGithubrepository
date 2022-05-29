package com.example.samplegithubrepository.presentation.composable_view


import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.samplegithubrepository.R

@Composable
fun CommonError(text: String, onRefreshClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            /* Text(
                 modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                 text = "¯\\_(ツ)_/¯",
                 style = TextStyle(fontSize = 55.sp)
             )*/

            Image(
                painter = painterResource(id = R.drawable.ic_vector_common_error),
                contentDescription = "Andy Rubin",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onRefreshClick()
                    }
            )
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                text = text,
                style = MaterialTheme.typography.h4
            )
        }

    }
}