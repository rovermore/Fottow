package com.fottow.fottow.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.user.usecase.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
): ViewModel(){

    private var _register = MutableStateFlow<Boolean>(false)
    val register: StateFlow<Boolean> get() = _register.asStateFlow()
    private var _error = MutableStateFlow<Boolean>(false)
    val error: StateFlow<Boolean> get() = _error.asStateFlow()

    fun registerUser(email: String, password: String, nickName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            registerUseCase.registerUser(email, password, nickName)
                .map {
                    _register.update { true }
                }.mapFailure {
                    _error.update { true }
                }
        }
    }
}