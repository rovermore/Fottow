package com.fottow.fottow.data.login

import com.fottow.fottow.data.base.APIErrorMapper
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.login.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginNetworkDatasource: LoginNetworkDatasource,
    private val apiErrorMapper: APIErrorMapper
): LoginRepository {
    override suspend fun logUser(user: String, password: String): Result<Boolean, Error> {
        return loginNetworkDatasource.logUser(user, password)
            .map {
                //TODO GUARDAR EL TOKEN EN UN SINGLETON
                true
            }
            .mapFailure {
                apiErrorMapper.map(it)
            }
    }
}