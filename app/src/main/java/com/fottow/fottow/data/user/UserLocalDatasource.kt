package com.fottow.fottow.data.user

class UserLocalDatasource {

    private var token: String = ""

    fun getToken() = token
    fun setToken(tkn: String) {
        token = tkn
    }
}