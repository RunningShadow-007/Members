package com.group.core.network.datasource.user.api

import com.group.core.model.NetworkResponse
import com.group.core.network.datasource.user.model.LoginEntity
import com.group.core.network.datasource.user.model.LoginRequest
import com.group.core.network.datasource.user.model.RegisterRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/13 13:18<br>
 * Desc: <br>
 */
internal interface LoginApi {
    @POST("familyhelp/register")
    fun register(@Body request: RegisterRequest): Flow<NetworkResponse<Any>>

    @POST("familyhelp/login")
    fun login(@Body request: LoginRequest): Flow<NetworkResponse<LoginEntity>>

    @POST("familyhelp/logout")
    fun logout(): Flow<NetworkResponse<Any>>
}