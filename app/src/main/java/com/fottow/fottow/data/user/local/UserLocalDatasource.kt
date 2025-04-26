package com.fottow.fottow.data.user.local

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result

class UserLocalDatasource(
    private val dataStore: FottowDataStore
) {

    companion object {
        const val TOKEN_KEY = "TOKEN_KEY"
        const val EMAIL_KEY = "EMAIL_KEY"
        const val NAME_KEY = "NAME_KEY"
    }


    fun getToken(): Result<String, Error> = dataStore.read<String>(TOKEN_KEY)

    fun setToken(tkn: String) {
        dataStore.save(TOKEN_KEY, tkn)
    }

    fun deleteToken() {
        dataStore.delete<String>(TOKEN_KEY)
    }

    fun getEmail(): Result<String, Error> = dataStore.read<String>(EMAIL_KEY)

    fun setEmail(email: String) {
        dataStore.save(EMAIL_KEY, email)
    }

    fun deleteEmail() {
        dataStore.delete<String>(NAME_KEY)
    }

    fun getName(): Result<String, Error> = dataStore.read<String>(NAME_KEY)

    fun setName(name: String) {
        dataStore.save(NAME_KEY, name)
    }

    fun deleteName() {
        dataStore.delete<String>(NAME_KEY)
    }

}