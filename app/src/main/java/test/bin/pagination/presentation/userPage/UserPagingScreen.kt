package test.bin.pagination.presentation.userPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems


@Composable
fun UserPagingScreen(
    viewModel: UserPagingViewModel
) {
    val users = viewModel.users.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Paging list",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp,
                color = Color.Blue
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                count = users.itemCount,
                key = { index -> users[index]?.id ?: index }
            ) { index ->
                users[index]?.let { user ->
                    UserItem(user)
                }
            }

            when {
                users.loadState.refresh is LoadState.Loading -> {
                    item {
                        FullScreenLoader()
                    }
                }

                users.loadState.refresh is LoadState.Error -> {
                    val error = (users.loadState.refresh as LoadState.Error).error
                    item {
                        ErrorItem(
                            message = error.localizedMessage ?: "Something went wrong",
                            onRetry = { users.retry() }
                        )
                    }
                }

                users.loadState.append is LoadState.Loading -> {
                    item {
                        BottomLoader()
                    }
                }

                users.loadState.append is LoadState.Error -> {
                    val error = (users.loadState.append as LoadState.Error).error
                    item {
                        ErrorItem(
                            message = error.localizedMessage ?: "Failed to load more",
                            onRetry = { users.retry() }
                        )
                    }
                }
            }
        }
    }
}
