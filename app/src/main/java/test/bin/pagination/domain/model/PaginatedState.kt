package test.bin.pagination.test.bin.pagination.domain.model

data class PaginatedState(
    val items: List<User> = emptyList(),
    val page: Int = 1,
    val isLoading: Boolean = false,
    val hasMore: Boolean = true,
    val error: String? = null
)
