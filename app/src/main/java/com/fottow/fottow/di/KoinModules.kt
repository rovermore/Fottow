package com.fottow.fottow.di

import com.fottow.fottow.FileResolver
import com.fottow.fottow.data.photo.PhotoNetworkDatasource
import com.fottow.fottow.data.photo.PhotoRepositoryImpl
import com.fottow.fottow.data.photo.base.FottowKtorClient
import com.fottow.fottow.domain.photo.repository.PhotoRepository
import com.fottow.fottow.domain.photo.usecase.UploadPhotoUseCase
import com.fottow.fottow.presentation.main.MainViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::FileResolver)

    viewModelOf(::MainViewModel)

    factory { UploadPhotoUseCase(get()) }

    single<PhotoRepository> { PhotoRepositoryImpl() }

    factory { PhotoNetworkDatasource(get()) }

    single { FottowKtorClient() }

}