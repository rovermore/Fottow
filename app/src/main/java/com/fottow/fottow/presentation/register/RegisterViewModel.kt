package com.fottow.fottow.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.base.then
import com.fottow.fottow.domain.user.usecase.RegisterUseCase
import com.fottow.fottow.presentation.error.ErrorUi
import com.fottow.fottow.presentation.error.ErrorUiMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val errorUiMapper: ErrorUiMapper
): ViewModel(){

    private var _register = MutableStateFlow<Boolean>(false)
    val register: StateFlow<Boolean> get() = _register.asStateFlow()
    private val _onError = MutableStateFlow<ErrorUi>(ErrorUi.None)
    val onError: StateFlow<ErrorUi> get() = _onError
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun registerUser(email: String, password: String, nickName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            registerUseCase.registerUser(email, password, nickName)
                .map {
                    _register.update { true }
                }.mapFailure {
                    _onError.value = errorUiMapper.map(it)
                }.then {
                    _isLoading.value = false
                }
        }
    }
}