package com.dedan.ekitabu.ui.index

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dedan.ekitabu.utils.BottomNavigationScreens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IndexActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}

@Composable
fun MainScreen() {
    val navController= rememberNavController()
    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Frankendroid,
        BottomNavigationScreens.Pumpkin,
        BottomNavigationScreens.Ghost,
        BottomNavigationScreens.ScaryBag
    )
    Scaffold(
        bottomBar = {
            SpookyAppBottomNavigation(navController, bottomNavigationItems)
        },
    ) {
        NavHost(navController = navController, startDestination = BottomNavigationScreens.Frankendroid.route) {
            composable(BottomNavigationScreens.Frankendroid.route) {
                Text(text = "am home honey")
            }
            composable(BottomNavigationScreens.Pumpkin.route) {
                Text(text = "atefory ziko")
            }
            composable(BottomNavigationScreens.Ghost.route) {
                Text(text="hey authors")
            }
            composable(BottomNavigationScreens.ScaryBag.route) {
                Text(text="my profile")
            }
        }
    }
}


@Composable
private fun SpookyAppBottomNavigation(
    navController: NavController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon,null) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true,
                alwaysShowLabel = false, // This hides the title for the unselected items
                onClick = {

                        navController.navigate(screen.route){
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }

                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavController): NavDestination? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavController
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BottomNavigationScreens.Frankendroid.route) {
        composable(BottomNavigationScreens.Frankendroid.route) {
            Text(text = "am home honey")
        }
        composable(BottomNavigationScreens.Pumpkin.route) {
            Text(text = "atefory ziko")
        }
        composable(BottomNavigationScreens.Ghost.route) {
            Text(text="hey authors")
        }
        composable(BottomNavigationScreens.ScaryBag.route) {
            Text(text="my profile")
        }
    }
}