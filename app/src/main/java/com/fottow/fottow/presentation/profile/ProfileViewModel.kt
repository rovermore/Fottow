package com.fottow.fottow.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.user.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _onLogout = MutableStateFlow(false)
    val onLogout: StateFlow<Boolean> get() = _onLogout

    fun logout() {
        viewModelScope.launch {
            loginUseCase.logout()
                .map {
                    _onLogout.value = true
                }
        }
    }
}