package test.bin.pagination

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import test.bin.pagination.di.appModule
import test.bin.pagination.di.networkModule
import test.bin.pagination.di.repoModule
import test.bin.pagination.di.viewModelModule


class PaginationAPP : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger(Level.DEBUG) // Optional, for logging purposes
            androidContext(this@PaginationAPP) // Application context for Koin
            modules(
                viewModelModule,
                appModule,
                networkModule,
                repoModule
            ) // modules
        }
    }
}