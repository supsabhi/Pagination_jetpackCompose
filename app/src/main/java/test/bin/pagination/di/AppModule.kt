package test.bin.pagination.di

import org.koin.dsl.module
import test.bin.pagination.core.ResourcesProvider

val appModule = module {
    single {
        ResourcesProvider(get())
    }
}
