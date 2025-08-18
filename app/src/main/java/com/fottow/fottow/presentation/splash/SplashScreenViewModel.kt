package com.fottow.fottow.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.base.then
import com.fottow.fottow.domain.user.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    val isUserLogged: StateFlow<Boolean> = flow {
            loginUseCase.isUserLogged()
                .map {
                    emit(it.isNotEmpty())
                }.mapFailure {
                     emit(false)
                }.then {
                    _loading.value = false
                }
    }.stateIn(viewModelScope, SharingStarted.Lazily, false)

    private val isFirstInstall: StateFlow<Boolean> = flow {
        loginUseCase.isFirstInstall()
            .map {
                emit(it)
            }
            .mapFailure {
                emit(true)
            }
    }.stateIn(viewModelScope, SharingStarted.Lazily, true)

    private var _loading = MutableStateFlow<Boolean>(true)
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    val state = combine(
        isUserLogged,
        isFirstInstall,
        loading
    ) { isUserLogged, isFirstInstall, loading ->
        SplashScreenState(
            isUserLogged = isUserLogged,
            isFirstInstall = isFirstInstall,
            loading = loading
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, SplashScreenState())

}

data class SplashScreenState(
    val isUserLogged: Boolean = false,
    val isFirstInstall: Boolean = true,
    val loading: Boolean = true
)