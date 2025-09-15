package com.fottow.fottow.data.user.local

import com.fottow.fottow.data.base.Crypto
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Failure
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.Success
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.user.model.User
import kotlinx.serialization.json.Json
import java.util.Base64

class UserLocalDatasource(
    private val dataStore: FottowDataStore
) {

    companion object {
        const val TOKEN_KEY = "TOKEN_KEY"
        const val USER_KEY = "USER_KEY"
        const val FIRST_INSTALL_KEY = "FIRST_INSTALL_KEY"
        const val FCM_TOKEN_KEY = "FCM_TOKEN_KEY"
    }

    private val json = Json // singleton de kotlinx.serialization

    fun getToken(): Result<String, Error> {
        return when (val result = dataStore.read<String>(TOKEN_KEY)) {
            is Success -> {
                val encodedToken = result.value
                if (encodedToken.isEmpty()) {
                    Failure(Error.UncompletedOperation("No token found"))
                } else {
                    try {
                        val decryptedToken =
                            Crypto.decrypt(Base64.getDecoder().decode(encodedToken))
                        Success(decryptedToken)
                    } catch (e: Exception) {
                        Failure(Error.UncompletedOperation("Token corrupt or invalid"))
                    }
                }
            }
            is Failure -> Failure(result.reason)
        }
    }

    fun setToken(tkn: String) {
        val encryptedToken = Base64.getEncoder().encodeToString(Crypto.encrypt(tkn))
        dataStore.save(TOKEN_KEY, encryptedToken)
    }

    fun deleteToken() {
        dataStore.delete<String>(TOKEN_KEY)
    }

    fun setUser(user: User) {
        val userString = json.encodeToString(user)
        val encryptedUserString = Base64.getEncoder().encodeToString(Crypto.encrypt(userString))
        dataStore.save(USER_KEY, encryptedUserString)
    }

    fun getUser(): Result<User, Error> {
        return when (val result = dataStore.read<String>(USER_KEY)) {
            is Success -> {
                val decryptedUserString = Crypto.decrypt(Base64.getDecoder().decode(result.value))
                val user = try {
                    json.decodeFromString<User>(decryptedUserString)
                } catch (e: Exception) {
                    return Failure(Error.UncompletedOperation("Invalid user data"))
                }
                Success(user)
            }
            is Failure -> Failure(result.reason)
        }
    }

    fun deleteUser() {
        dataStore.delete<String>(USER_KEY)
    }

    fun isFirstInstall(): Result<Boolean, Error> {
        return when (val result = dataStore.read<Boolean>(FIRST_INSTALL_KEY)) {
            is Success -> result
            is Failure -> Success(true)
        }
    }

    fun setFirstInstall() {
        dataStore.save(FIRST_INSTALL_KEY, false)
    }

    fun setFCMToken(token: String) {
        dataStore.save(FCM_TOKEN_KEY, token)
    }

    fun getFCMToken(): Result<String, Error> {
        return dataStore.read<String>(FCM_TOKEN_KEY).mapFailure {
            Error.UncompletedOperation("No FCM token found")
        }
    }
}