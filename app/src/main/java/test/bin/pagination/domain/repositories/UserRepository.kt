package test.bin.pagination.domain.repositories

import test.bin.pagination.test.bin.pagination.domain.model.User


interface UserRepository {
    suspend fun getUsers(page: Int, pageSize: Int): List<User>
    suspend fun getSnapShotUsers(skip: Int, pageSize: Int): List<User>

}

