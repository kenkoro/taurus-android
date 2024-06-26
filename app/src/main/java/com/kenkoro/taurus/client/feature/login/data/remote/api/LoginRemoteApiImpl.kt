package com.kenkoro.taurus.client.feature.login.data.remote.api

import com.kenkoro.taurus.client.feature.login.data.remote.dto.LoginDto
import com.kenkoro.taurus.client.feature.shared.Urls
import com.kenkoro.taurus.client.feature.shared.data.remote.dto.TokenDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LoginRemoteApiImpl(
  private val client: HttpClient,
) : LoginRemoteApi {
  override suspend fun login(dto: LoginDto): TokenDto =
    client.post {
      url(Urls.LOGIN)
      contentType(ContentType.Application.Json)
      setBody(dto)
    }.body<TokenDto>()
}