package test.bin.pagination.test.bin.pagination.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import test.bin.pagination.domain.repositories.UserRepository
import test.bin.pagination.test.bin.pagination.domain.model.User

class UserPagingSource(
    private val repository: UserRepository
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val users = repository.getUsers(page, params.loadSize)

            LoadResult.Page(
                data = users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchor ->
            val page = state.closestPageToPosition(anchor)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}
