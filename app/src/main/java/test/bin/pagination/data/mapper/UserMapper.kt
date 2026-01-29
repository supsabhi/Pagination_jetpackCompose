package test.bin.pagination.test.bin.pagination.data.mapper

import test.bin.pagination.test.bin.pagination.domain.model.User
import test.bin.pagination.test.bin.pagination.domain.model.UserDto

fun UserDto.toDomain(): User {
    return User(
        id = id,
        username = "$firstName $lastName",
        image = image
    )
}