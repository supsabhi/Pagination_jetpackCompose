package test.bin.pagination.data.network_util


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import test.bin.pagination.domain.network_util.UserApi
import test.bin.pagination.test.bin.pagination.domain.model.ApiResponse
import test.bin.pagination.test.bin.pagination.domain.model.UserDto

class UserApiImpl(
    private val client: HttpClient = HttpClient(Android) {

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }

        install(HttpRedirect) {
            checkHttpMethod = false
        }

    }
) : UserApi {

    override suspend fun getSnapUsers(page: Int, pageSize: Int): List<UserDto> {
        val skip = (page - 1) * pageSize

        return client.get("https://dummyjson.com/users") {
            parameter("limit", page)
            parameter("skip", skip)
        }
            .body<ApiResponse>()
            .users

    }

    override suspend fun getUsers(page: Int, pageSize: Int): List<UserDto> {
        val skip = (page - 1) * pageSize

        return client.get("https://dummyjson.com/users") {
            parameter("limit", pageSize)
            parameter("skip", skip)
        }
            .body<ApiResponse>()
            .users

    }

}
