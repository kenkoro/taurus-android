package com.kenkoro.taurus.mobile_client.feature_login.data.source.remote

data class UserDto(
  val id: Int,
  val username: String,
  val email: String,
  val firstName: String,
  val lastName: String,
  val gender: String,
  val image: String,
  val token: String
)
