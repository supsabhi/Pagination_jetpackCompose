package test.bin.pagination.presentation.navigation

sealed class Screen(val route: String) {
    object UserPagingScreen : Screen(USERPAGINGSCREEN)
    object UserSnapshotScreen : Screen(USERSNAPSHOTSCREEN)
    object HomeScreen : Screen(HOMESCREEN)
}