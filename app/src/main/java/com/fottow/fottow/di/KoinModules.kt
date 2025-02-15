package com.fottow.fottow.di

import com.fottow.fottow.FileResolver
import com.fottow.fottow.data.base.APIErrorMapper
import com.fottow.fottow.data.base.CallExecutor
import com.fottow.fottow.data.photo.PhotoNetworkDatasource
import com.fottow.fottow.data.photo.PhotoRepositoryImpl
import com.fottow.fottow.data.base.FottowKtorClient
import com.fottow.fottow.data.base.NetworkExceptionsMapper
import com.fottow.fottow.data.login.LoginNetworkDatasource
import com.fottow.fottow.data.login.LoginRepositoryImpl
import com.fottow.fottow.domain.login.repository.LoginRepository
import com.fottow.fottow.domain.login.usecase.LoginUseCase
import com.fottow.fottow.domain.photo.repository.PhotoRepository
import com.fottow.fottow.domain.photo.usecase.UploadPhotoUseCase
import com.fottow.fottow.presentation.main.MainViewModel
import com.fottow.fottow.presentation.login.LoginViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::FileResolver)

    viewModelOf(::MainViewModel)
    viewModelOf(::LoginViewModel)

    factory { UploadPhotoUseCase(get()) }

    factory { LoginUseCase(get())}

    single { FottowKtorClient() }

    factory { NetworkExceptionsMapper() }

    factory { APIErrorMapper() }

    factory { CallExecutor(get()) }

    factory { PhotoNetworkDatasource(get(), get()) }

    factory { LoginNetworkDatasource(get(), get())}

    single<PhotoRepository> { PhotoRepositoryImpl(get(), get()) }

    single<LoginRepository> {LoginRepositoryImpl(get(), get())}

}