package test.bin.pagination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import test.bin.pagination.presentation.navigation.Navigation
import test.bin.pagination.presentation.uiUtils.ObserveAsEvents
import test.bin.pagination.presentation.uiUtils.SnackBarController


@Composable
fun App() {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()



    ObserveAsEvents(
        flow = SnackBarController.events,
        snackBarHostState
    ) { event ->
        scope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()
            val result = snackBarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier
                    .wrapContentSize()
            )
        },
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),

        ) { innerPadding ->
        Navigation()
    }
}