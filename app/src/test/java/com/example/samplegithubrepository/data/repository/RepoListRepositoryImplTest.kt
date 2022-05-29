package com.example.samplegithubrepository.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule


class RepoListRepositoryImplTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

}