package test.bin.pagination.test.bin.pagination.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class UserDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val image: String
)