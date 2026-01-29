package test.bin.pagination.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import test.bin.pagination.presentation.userPage.UserPagingScreen
import test.bin.pagination.presentation.userPage.UserPagingViewModel
import test.bin.pagination.presentation.userPage.UserSnapshotScreen
import test.bin.pagination.presentation.userPage.UserSnapshotViewModel
import test.bin.pagination.test.bin.pagination.presentation.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }

        composable(Screen.UserSnapshotScreen.route) {
            val snapshot_viewModel = koinViewModel<UserSnapshotViewModel>()
            UserSnapshotScreen(snapshot_viewModel)
        }

        composable(Screen.UserPagingScreen.route) {
            val viewModel = koinViewModel<UserPagingViewModel>()
            UserPagingScreen(viewModel)
        }
    }
}
