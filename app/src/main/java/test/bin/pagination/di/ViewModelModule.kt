package test.bin.pagination.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import test.bin.pagination.presentation.userPage.UserPagingViewModel
import test.bin.pagination.presentation.userPage.UserSnapshotViewModel


val viewModelModule = module {
    viewModel { UserPagingViewModel(get()) }
    viewModel { UserSnapshotViewModel(get()) }


}