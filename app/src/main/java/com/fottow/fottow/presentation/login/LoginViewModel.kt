package com.fottow.fottow.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.user.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private var _login = MutableStateFlow<Boolean>(false)
    val login: StateFlow<Boolean> get() = _login.asStateFlow()
    private var _error = MutableStateFlow<Boolean>(false)
    val error: StateFlow<Boolean> get() = _error.asStateFlow()

    fun logUser(userName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.logUser(userName, password)
                .map {
                    _login.update { true }
                }.mapFailure {
                    _error.update { true }
                }
        }
    }

}