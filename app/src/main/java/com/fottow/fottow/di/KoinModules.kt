package com.fottow.fottow.di

import com.fottow.fottow.FileResolver
import com.fottow.fottow.presentation.MainViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    singleOf(::FileResolver)

    viewModelOf(::MainViewModel)
}