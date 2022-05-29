package com.example.samplegithubrepository.presentation.composable_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.samplegithubrepository.R
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem

@Composable
fun RepoItem(repoList: RepoListItem, index: Int, onViewClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = onViewClick),

        elevation = 2.dp,
        shape = RoundedCornerShape(4.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(colorResource(id = R.color.dark_blue))) {
                        append(stringResource(R.string.name_))
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(repoList.name)
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.dark_blue))) {
                        append(stringResource(R.string.desc))
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(repoList.description)
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
            )
        }
    }
}