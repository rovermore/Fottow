package com.fottow.fottow.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.base.then
import com.fottow.fottow.domain.user.model.User
import com.fottow.fottow.domain.user.usecase.LoginUseCase
import com.fottow.fottow.presentation.error.ErrorUi
import com.fottow.fottow.presentation.error.ErrorUiMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val errorUiMapper: ErrorUiMapper
): ViewModel() {

    private var _user = MutableStateFlow<User>(User())
    val user: StateFlow<User> get() = _user.asStateFlow()
    private val _onError = MutableStateFlow<ErrorUi>(ErrorUi.None)
    val onError: StateFlow<ErrorUi> get() = _onError
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun logUser(userName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            loginUseCase.logUser(userName, password)
                .map { result ->
                    _user.update { it.copy(
                        email = result.email,
                        name = result.name,
                        profileImage = result.profileImage)
                    }
                }.mapFailure {
                    _onError.value = errorUiMapper.map(it)
                }.then {
                    _isLoading.value = false
                }
        }
    }

}