package com.lucgu.pcstest.presentation.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.lucgu.pcstest.domain.entities.UserEntity
import com.lucgu.pcstest.presentation.feature.navigation.Route
import com.lucgu.pcstest.presentation.view.ErrorView
import com.lucgu.pcstest.presentation.view.ListUserView

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
) {
    val viewState = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect {
            when (it) {
                is HomeViewEvent.NavigateToDetail -> {
                    navController.currentBackStackEntry?.savedStateHandle?.set("data", it.user)
                    navController.navigate(Route.DETAIL_ROUTE) {
                        restoreState = true
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "List User", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(color = Color.White),
            ) {
                Content(
                    isLoading = viewState.isLoading,
                    data = viewState.listUser,
                    errorMessage = viewState.errorMessage,
                    onRetry = {
                        viewModel.fetchListUser()
                    },
                    onClickItem = {
                        viewModel.navigateToDetail(it)
                    }
                )
            }
        }
    )
}

@Composable
private fun Content(
    isLoading: Boolean,
    data: List<UserEntity>,
    errorMessage: String,
    onRetry: () -> Unit,
    onClickItem: (UserEntity) -> Unit
) {

    if (isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.Red)
        }
    } else if (data.isNotEmpty()) {
        ListUserView(
            data = data,
            onClickItem = {
                onClickItem(it)
            }
        )
    } else if (errorMessage.isNotEmpty()) {
        ErrorView(onRetry = {
            onRetry()
        })
    }
}