package test.bin.pagination.di

import android.content.Context
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import test.bin.pagination.data.network_util.UserApiImpl
import test.bin.pagination.domain.network_util.UserApi


val authInterceptor: suspend HttpRequestBuilder.(
    Context, CoroutineScope
) -> Unit = { context, scope ->

}

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }

            engine {
                connectTimeout = 60000
                socketTimeout = 60000
            }
            defaultRequest {
                header("Accept", "application/json")
                header("Content-Type", "application/json")
                header("User-Agent", "Ktor-Android")
            }
            //Http Response
            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }
            install(Logging) {
                level = LogLevel.ALL
            }

        }
    }

    // Ktor
    factory<UserApi> { UserApiImpl(get()) }
}