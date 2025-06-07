package com.fottow.fottow.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.user.model.User
import com.fottow.fottow.domain.user.usecase.LoginUseCase
import com.fottow.fottow.domain.user.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val loginUseCase: LoginUseCase,
    private val userUserUseCase: UserUseCase
): ViewModel() {

    private val _onLogout = MutableStateFlow(false)
    private val _userInfo = MutableStateFlow<User>(User())

    init {
        getUserInfo()
    }

   val state = combine(
        _userInfo,
        _onLogout
   ) { userInfo, onLogout ->
        ProfileState(
            userInfo = userInfo,
            onLogout = onLogout
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProfileState()
   )

    private fun getUserInfo() {
        viewModelScope.launch {
            userUserUseCase.getUser()
                .map { result ->
                    _userInfo.update { it.copy(email = result.email, name = result.name)}
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            loginUseCase.logout()
                .map {
                    _onLogout.value = true
                }
        }
    }
}

data class ProfileState(
    val userInfo: User = User(),
    val onLogout: Boolean = false
)