package com.fottow.fottow.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.base.get
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.user.model.User
import com.fottow.fottow.domain.user.usecase.LoginUseCase
import com.fottow.fottow.domain.user.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val loginUseCase: LoginUseCase,
    private val userUserUseCase: UserUseCase
): ViewModel() {

    private val _onLogout = MutableStateFlow(false)
    val onLogout: StateFlow<Boolean> get() = _onLogout

    val userInfo: StateFlow<User> = flow<User> {
        userUserUseCase.getUser()
            .get()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = User()
    )

    fun logout() {
        viewModelScope.launch {
            loginUseCase.logout()
                .map {
                    _onLogout.value = true
                }
        }
    }
}