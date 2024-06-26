package com.kenkoro.taurus.client.feature.login.data.remote.repository

import com.kenkoro.taurus.client.feature.login.data.remote.api.LoginRemoteApi
import com.kenkoro.taurus.client.feature.login.data.remote.dto.LoginDto
import com.kenkoro.taurus.client.feature.shared.data.remote.dto.TokenDto

interface LoginRepository {
  companion object {
    fun create(api: LoginRemoteApi): LoginRepositoryImpl = LoginRepositoryImpl(api)
  }

  suspend fun login(dto: LoginDto): Result<TokenDto>
}