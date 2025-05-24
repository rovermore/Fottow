package com.fottow.fottow.di

import android.content.Context
import androidx.work.WorkerParameters
import com.fottow.fottow.FileResolver
import com.fottow.fottow.data.base.APIErrorMapper
import com.fottow.fottow.data.base.CallExecutor
import com.fottow.fottow.data.photo.PhotoNetworkDatasource
import com.fottow.fottow.data.photo.PhotoRepositoryImpl
import com.fottow.fottow.data.base.FottowKtorClient
import com.fottow.fottow.data.base.NetworkExceptionsMapper
import com.fottow.fottow.data.user.local.UserLocalDatasource
import com.fottow.fottow.data.user.UserNetworkDatasource
import com.fottow.fottow.data.user.UserRepositoryImpl
import com.fottow.fottow.data.user.local.FottowDataStore
import com.fottow.fottow.domain.user.repository.UserRepository
import com.fottow.fottow.domain.user.usecase.LoginUseCase
import com.fottow.fottow.domain.photo.repository.PhotoRepository
import com.fottow.fottow.domain.photo.usecase.PhotoUseCase
import com.fottow.fottow.domain.user.usecase.RegisterUseCase
import com.fottow.fottow.domain.user.usecase.UserUseCase
import com.fottow.fottow.presentation.upload.UploadViewModel
import com.fottow.fottow.presentation.login.LoginViewModel
import com.fottow.fottow.presentation.register.RegisterViewModel
import com.fottow.fottow.presentation.gallery.GalleryViewModel
import com.fottow.fottow.presentation.splash.SplashScreenViewModel
import com.fottow.fottow.presentation.identification.IdentificationViewModel
import com.fottow.fottow.presentation.profile.ProfileViewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::FileResolver)

    viewModelOf(::UploadViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::GalleryViewModel)
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::IdentificationViewModel)
    viewModelOf(::ProfileViewModel)

    factory { PhotoUseCase(get()) }

    factory { LoginUseCase(get())}

    factory { RegisterUseCase(get()) }

    factory { UserUseCase(get()) }

    single { FottowKtorClient(get()) }

    factory { NetworkExceptionsMapper() }

    factory { APIErrorMapper() }

    factory { CallExecutor(get()) }

    factory { PhotoNetworkDatasource(get(), get()) }

    factory { UserNetworkDatasource(get(), get())}

    single { UserLocalDatasource(get()) }

    single { FottowDataStore(get()) }

    single<PhotoRepository> { PhotoRepositoryImpl(get(), get()) }

    single<UserRepository> {UserRepositoryImpl(get(), get(), get())}

}