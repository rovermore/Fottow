package com.fottow.fottow.data.user.local

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result

class UserLocalDatasource(
    private val dataStore: FottowDataStore
) {

    companion object {
        const val TOKEN_KEY = "TOKEN_KEY"
    }


    fun getToken(): Result<String, Error> = dataStore.read<String>(TOKEN_KEY)

    fun setToken(tkn: String) {
        dataStore.save(TOKEN_KEY, tkn)
    }

    fun deleteToken() {
        dataStore.delete<String>(TOKEN_KEY)
    }
}