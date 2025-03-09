package com.fottow.fottow.di

import com.fottow.fottow.FileResolver
import com.fottow.fottow.data.base.APIErrorMapper
import com.fottow.fottow.data.base.CallExecutor
import com.fottow.fottow.data.photo.PhotoNetworkDatasource
import com.fottow.fottow.data.photo.PhotoRepositoryImpl
import com.fottow.fottow.data.base.FottowKtorClient
import com.fottow.fottow.data.base.NetworkExceptionsMapper
import com.fottow.fottow.data.user.UserLocalDatasource
import com.fottow.fottow.data.user.UserNetworkDatasource
import com.fottow.fottow.data.user.UserRepositoryImpl
import com.fottow.fottow.domain.user.repository.UserRepository
import com.fottow.fottow.domain.user.usecase.LoginUseCase
import com.fottow.fottow.domain.photo.repository.PhotoRepository
import com.fottow.fottow.domain.photo.usecase.UploadPhotoUseCase
import com.fottow.fottow.domain.user.usecase.RegisterUseCase
import com.fottow.fottow.presentation.main.MainViewModel
import com.fottow.fottow.presentation.login.LoginViewModel
import com.fottow.fottow.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::FileResolver)

    viewModelOf(::MainViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)

    factory { UploadPhotoUseCase(get()) }

    factory { LoginUseCase(get())}

    factory { RegisterUseCase(get()) }

    single { FottowKtorClient() }

    factory { NetworkExceptionsMapper() }

    factory { APIErrorMapper() }

    factory { CallExecutor(get()) }

    factory { PhotoNetworkDatasource(get(), get()) }

    factory { UserNetworkDatasource(get(), get())}

    single { UserLocalDatasource() }

    single<PhotoRepository> { PhotoRepositoryImpl(get(), get()) }

    single<UserRepository> {UserRepositoryImpl(get(), get(), get())}

}