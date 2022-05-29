package com.example.samplegithubrepository.presentation.repo_detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samplegithubrepository.R
import com.example.samplegithubrepository.presentation.composable_view.CoilImageLoading
import com.example.samplegithubrepository.presentation.composable_view.CommonError
import com.example.samplegithubrepository.presentation.composable_view.DetailTopAppBar

import com.example.samplegithubrepository.ui.theme.SampleGithubrepositoryTheme
import com.example.samplegithubrepository.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoDetailActivity : ComponentActivity() {

    private val repoDetailViewModel: RepoDetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleGithubrepositoryTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                    ) {
                        DetailTopAppBar(title = repoDetailViewModel.mName.value) {
                            finish()
                        }
                        getIntentData()
                    }
                }
            }
        }
    }

    @SuppressLint("ComposableNaming")
    @Composable
    private fun getIntentData() {
        if (intent != null && intent.hasExtra(Constants.REPOLIST_TO_REPODETAIL)) {
            val fullName: String =
                intent.getSerializableExtra(Constants.REPOLIST_TO_REPODETAIL) as String
            repoDetailViewModel.full_name.value = fullName
            repoDetailViewModel.getRepoDetail()
            InitCallObservable()

        }
    }

    @Composable
    private fun InitCallObservable() {
        val state by repoDetailViewModel.repoDetail.collectAsState()
        if (state.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        if (state.error?.error == true) {
            CommonError(
                text = state.error!!.getErrorMessage(),
                onRefreshClick = { repoDetailViewModel.getRepoDetail() })
        }

        state.data?.let {
            DrawDetailPage()
        }

    }

    @Preview
    private
    @Composable
    fun DrawDetailPage() {
        val scrollState= rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                CoilImageLoading(url = repoDetailViewModel.mAvatarurl.value)

                Text(
                    repoDetailViewModel.mOwnerLogin.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.orange),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 4.dp, top = 10.dp, bottom = 4.dp, end = 4.dp)
                )

                Text(
                    repoDetailViewModel.mDescription.value,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.dark_blue),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 4.dp, top = 15.dp, bottom = 15.dp, end = 4.dp)
                )
            }

            Row (
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 5.dp)
                    .fillMaxWidth(),
             verticalAlignment = Alignment.CenterVertically

            ){
                Icon(
                    painter = painterResource(R.drawable.ic_vector_star),
                    contentDescription = "print",
                            tint= Color.Unspecified
                )
                Text(
                    repoDetailViewModel.mStargazersCount.value.toString()+" "+ stringResource(R.string.stars),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.dark_blue),
                    modifier = Modifier
                        .padding(start = 6.dp, end = 4.dp)


                )
            }

            Row (
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically

            ){
                Icon(
                    painter = painterResource(R.drawable.ic_vector_fork),
                    contentDescription = "print",
                    tint= Color.Unspecified
                )
                Text(
                    repoDetailViewModel.mForksCount.value.toString()+ stringResource(R.string.forks),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.dark_blue),
                    modifier = Modifier
                        .padding(start = 6.dp, end = 4.dp)


                )
            }


        }
    }
}

