package com.group.core.network.datasource.user.api

import com.group.core.model.NetworkResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/13 16:41<br>
 * Desc: <br>
 */
internal interface UserApi {
    @GET("familyhelp/user")
    fun getUserInfo(): Flow<NetworkResponse<Any>>

    @GET("familyhelp/user/getUserInfoByPhone")
    fun getUserInfoByPhone(): Flow<NetworkResponse<Any>>


    data class UpdateUserInfoRequest(private val name: String, private val portrait: String)

    @POST("user/update")
    fun updateUserInfo(@Body request: UpdateUserInfoRequest): Flow<NetworkResponse<Any>>

    @Multipart
    @POST("user/portrait/upload")
    fun uploadPortrait(@Part file: MultipartBody.Part? = null): Flow<NetworkResponse<Any>>
}