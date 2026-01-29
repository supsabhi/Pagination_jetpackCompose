package test.bin.pagination.domain.network_util

import test.bin.pagination.test.bin.pagination.domain.model.UserDto


interface UserApi {
    suspend fun getUsers(page: Int, pageSize: Int): List<UserDto>
    suspend fun getSnapUsers(page: Int, pageSize: Int): List<UserDto>

}