package com.group.core.network.datasource.user

import com.group.core.model.NetworkResponse
import com.group.core.network.datasource.user.model.LoginEntity
import com.group.core.network.datasource.user.model.LoginRequest
import com.group.core.network.datasource.user.model.RegisterRequest
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/13 22:30<br>
 * Desc: <br>
 */
interface UserDataSource {
    fun register(request: RegisterRequest): Flow<NetworkResponse<Any>>
    fun login(loginRequest: LoginRequest): Flow<NetworkResponse<LoginEntity>>
    fun logout(): Flow<NetworkResponse<Any>>
    fun getUserInfo(): Flow<NetworkResponse<Any>>
    fun getUserInfoByPhone(): Flow<NetworkResponse<Any>>
    fun updateUserInfo(name: String, portrait: String): Flow<NetworkResponse<Any>>
    fun uploadPortrait(file: MultipartBody.Part? = null): Flow<NetworkResponse<Any>>
}