package test.bin.pagination.test.bin.pagination.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val users: List<UserDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
