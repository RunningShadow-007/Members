package com.group.core.network.datasource.user

import com.group.core.model.NetworkResponse
import com.group.core.network.datasource.user.api.LoginApi
import com.group.core.network.datasource.user.api.UserApi
import com.group.core.network.datasource.user.model.LoginEntity
import com.group.core.network.datasource.user.model.LoginRequest
import com.group.core.network.datasource.user.model.RegisterRequest
import com.group.core.network.di.NetworkModule.Companion.provideService
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/13 22:33<br>
 * Desc: <br>
 */
@Singleton
internal class RemoteUserDataSource @Inject constructor(private val retrofit: Retrofit) :
    UserDataSource {

    private val userApi: UserApi by lazy {
        retrofit.provideService(UserApi::class.java)
    }
    private val loginApi: LoginApi by lazy {
        retrofit.provideService(LoginApi::class.java)
    }

    override fun register(
        request: RegisterRequest
    ): Flow<NetworkResponse<Any>> {
        return loginApi.register(request)
    }

    override fun login(
        request: LoginRequest
    ): Flow<NetworkResponse<LoginEntity>> {
        return loginApi.login(request)
    }

    override fun logout(): Flow<NetworkResponse<Any>> {
        return loginApi.logout()
    }

    override fun getUserInfo(): Flow<NetworkResponse<Any>> {
        return userApi.getUserInfo()
    }

    override fun getUserInfoByPhone(): Flow<NetworkResponse<Any>> {
        return userApi.getUserInfoByPhone()
    }

    override fun updateUserInfo(
        name: String,
        portrait: String
    ): Flow<NetworkResponse<Any>> {
        return userApi.updateUserInfo(UserApi.UpdateUserInfoRequest(name, portrait))
    }

    override fun uploadPortrait(file: MultipartBody.Part?): Flow<NetworkResponse<Any>> {
        return userApi.uploadPortrait(file)
    }

}

