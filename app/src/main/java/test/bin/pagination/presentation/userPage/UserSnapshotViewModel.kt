package test.bin.pagination.presentation.userPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import test.bin.pagination.domain.repositories.UserRepository
import test.bin.pagination.test.bin.pagination.domain.model.PaginatedState

class UserSnapshotViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PaginatedState())
    val state = _state.asStateFlow()

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (_state.value.isLoading || !_state.value.hasMore) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                val limit = 10
                val skip = _state.value.items.size

                val users = repository.getSnapShotUsers(limit, skip)

                _state.update {
                    it.copy(
                        items = it.items + users,
                        page = it.page + 1,
                        isLoading = false,
                        hasMore = users.isNotEmpty()
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }
}
