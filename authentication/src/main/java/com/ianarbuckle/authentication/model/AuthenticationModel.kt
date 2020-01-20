package com.ianarbuckle.authentication.model

data class AuthBody(val uuid: String?,
                    val email: String,
                    val password: String,
                    val isRefresh: Boolean)

data class Authentication(val username: String,
                          val uuid: String,
                          val email: String,
                          val password: String,
                          val roles: Set<Role>)

data class Role(val role: String)

data class AuthenticationToken(val uuid: String, val token: String)

data class User(val username: String? = null,
                val uuid: String? = null,
                val email: String? = null,
                var password: String? = null,
                var isEnabled: Boolean = false,
                var roles: Set<Role>? = null)