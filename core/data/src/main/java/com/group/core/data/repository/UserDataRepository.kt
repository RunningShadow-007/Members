package com.group.core.data.repository

import com.group.core.model.Result
import com.group.core.network.datasource.user.model.LoginEntity
import com.group.core.network.datasource.user.model.LoginRequest
import com.group.core.network.datasource.user.model.RegisterRequest
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/14 11:13<br>
 * Desc: <br>
 */
interface UserDataRepository {
    fun register(request: RegisterRequest): Flow<Result<Any>>
    fun login(loginRequest: LoginRequest): Flow<Result<LoginEntity>>
    fun logout(): Flow<Result<Any>>
    fun getUserInfo(): Flow<Result<Any>>
    fun getUserInfoByPhone(): Flow<Result<Any>>
    fun updateUserInfo(name: String, portrait: String): Flow<Result<Any>>
    fun uploadPortrait(file: MultipartBody.Part? = null): Flow<Result<Any>>

}