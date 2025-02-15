package com.fottow.fottow.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.login.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    fun logUser(userName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.logUser(userName, password)
        }
    }

}