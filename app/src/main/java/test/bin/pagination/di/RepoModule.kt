package test.bin.pagination.di


import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import test.bin.pagination.data.network_util.ConnectivityImpl
import test.bin.pagination.data.repositories.UserRepositoryImpl
import test.bin.pagination.domain.network_util.Connectivity
import test.bin.pagination.domain.repositories.UserRepository


val repoModule = module {
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
    factory<UserRepository> { UserRepositoryImpl(get()) }
}