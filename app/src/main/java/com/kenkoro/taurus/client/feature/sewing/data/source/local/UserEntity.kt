package com.kenkoro.taurus.client.feature.sewing.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kenkoro.taurus.client.feature.sewing.data.util.UserProfile

@Entity
data class UserEntity(
  @PrimaryKey val id: Int,
  val subject: String,
  val password: String,
  val image: String,
  val firstName: String,
  val lastName: String,
  val email: String,
  val profile: UserProfile,
  val salt: String,
)