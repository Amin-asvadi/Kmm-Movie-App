package com.example.movies.android

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movies.android.common.Home
import com.example.movies.android.common.MovieAppBar
import com.example.movies.android.common.movieDestinatios
import com.example.movies.android.home.HomeScreen
import com.example.movies.android.home.HomeScreenViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isSystemDark = isSystemInDarkTheme()
    val scaffoldState = rememberScaffoldState()
    val statusBarColor = if (isSystemDark) {
        MaterialTheme.colors.primaryVariant
    } else {
        Color.Transparent
    }
    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinatios.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home
    Scaffold(scaffoldState = scaffoldState, topBar = {
        MovieAppBar(
            canNavigationBack = navController.previousBackStackEntry != null,
            currentScreen = currentScreen
        ) {
            navController.navigateUp()
        }
    }) { innerPadding ->

        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Home.routeWithArgs
        ) {
            composable(Home.routeWithArgs) {
                val homeViewModel: HomeScreenViewModel = koinViewModel()
                HomeScreen(uiState = homeViewModel.uiState, loadNextMovies = {
                    homeViewModel.loadMovies(forceReload = it)
                }, navigationToDetail ={

                })
            }
        }

    }
}