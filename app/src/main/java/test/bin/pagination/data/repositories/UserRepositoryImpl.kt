package test.bin.pagination.data.repositories


import test.bin.pagination.domain.network_util.UserApi
import test.bin.pagination.domain.repositories.UserRepository
import test.bin.pagination.test.bin.pagination.data.mapper.toDomain
import test.bin.pagination.test.bin.pagination.domain.model.User


class UserRepositoryImpl(
    private val api: UserApi
) : UserRepository {
    override suspend fun getSnapShotUsers(page: Int, pageSize: Int): List<User> {
        val skip = (page - 1) * pageSize

        return api.getSnapUsers(pageSize, skip).map { it.toDomain() }
    }

    override suspend fun getUsers(page: Int, pageSize: Int): List<User> {


        return api.getUsers(page, pageSize).map { it.toDomain() }
    }

}

