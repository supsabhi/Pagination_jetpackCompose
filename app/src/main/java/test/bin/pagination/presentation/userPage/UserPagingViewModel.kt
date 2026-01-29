package test.bin.pagination.presentation.userPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import test.bin.pagination.domain.repositories.UserRepository
import test.bin.pagination.test.bin.pagination.data.paging.UserPagingSource

class UserPagingViewModel(
    repository: UserRepository
) : ViewModel() {

    val users = Pager(
        PagingConfig(pageSize = 6)
    ) {
        UserPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}