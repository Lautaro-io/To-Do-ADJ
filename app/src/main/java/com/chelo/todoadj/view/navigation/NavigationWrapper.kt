package com.chelo.todoadj.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chelo.todoadj.view.home.HomeScreen
import com.chelo.todoadj.view.screens.HomeScreen
import com.chelo.todoadj.view.screens.SplashScreen
import com.chelo.todoadj.view.splashscreen.SplashScreen

@Composable
fun NavigationWrapper(){

    val navController = rememberNavController()

    NavHost(navController , startDestination = SplashScreen.ROUTE) {

        composable(SplashScreen.ROUTE){
            SplashScreen{
                navController.navigate(HomeScreen.ROUTE){
                    popUpTo(SplashScreen.ROUTE){
                        inclusive = true
                    }
                }
            }
        }

        composable(HomeScreen.ROUTE){
            HomeScreen()
        }

    }
}