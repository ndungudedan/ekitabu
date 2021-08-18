package com.dedan.ekitabu.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.dedan.ekitabu.R

sealed class BottomNavigationScreens(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Frankendroid : BottomNavigationScreens(Screen.Home.route, R.string.home_screen, Icons.Filled.Home)
    object Pumpkin : BottomNavigationScreens(Screen.Category.route, R.string.category_screen, Icons.Filled.Search)
    object Ghost : BottomNavigationScreens(Screen.Authors.route, R.string.authors_screen, Icons.Filled.Face)
    object ScaryBag : BottomNavigationScreens(Screen.Profile.route, R.string.profile_screen, Icons.Filled.Settings)
}


sealed class Screen(val route: String) {
    object Login: Screen("login_page")
    object Register: Screen("register_page")
    object Index: Screen("index_page")
    object Home: Screen("home")
    object Category: Screen("category")
    object Authors: Screen("authors")
    object Profile: Screen("profile")
}