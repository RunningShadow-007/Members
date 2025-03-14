package com.group.core.data.repository

import com.group.core.common.network.di.Dispatcher
import com.group.core.common.network.di.MDispatchers
import com.group.core.model.Result
import com.group.core.model.asResult
import com.group.core.network.datasource.user.UserDataSource
import com.group.core.network.datasource.user.model.LoginEntity
import com.group.core.network.datasource.user.model.LoginRequest
import com.group.core.network.datasource.user.model.RegisterRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/14 12:40<br>
 * Desc: 用户数据仓库实现类<br>
 */
class UserDataRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    @Dispatcher(MDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) :
    UserDataRepository {
    /**
     * 用户注册
     * @param request 注册请求参数
     * @return 封装为 Flow 的注册结果
     */
    override fun register(request: RegisterRequest): Flow<Result<Any>> {
        return userDataSource.register(request).asResult()
            .flowOn(ioDispatcher)
    }

    /**
     * 用户登录认证
     * @param loginRequest 登录请求参数（包含账号密码）
     * @return 包含用户凭证的响应流
     */
    override fun login(loginRequest: LoginRequest): Flow<Result<LoginEntity>> {
        return userDataSource.login(loginRequest).asResult()
            .flowOn(ioDispatcher)
    }

    override fun logout(): Flow<Result<Any>> {
        return userDataSource.logout().asResult().flowOn(ioDispatcher)
    }

    override fun getUserInfo(): Flow<Result<Any>> {
        return userDataSource.getUserInfo().asResult().flowOn(ioDispatcher)
    }

    override fun getUserInfoByPhone(): Flow<Result<Any>> {
        return userDataSource.getUserInfoByPhone().asResult().flowOn(ioDispatcher)
    }

    override fun updateUserInfo(
        name: String,
        portrait: String
    ): Flow<Result<Any>> {
        return userDataSource.updateUserInfo(name, portrait).asResult().flowOn(ioDispatcher)
    }

    /**
     * 上传用户头像
     * @param file 通过 Multipart 上传的图片文件（支持空值）
     * @return 上传结果响应流
     */
    override fun uploadPortrait(file: MultipartBody.Part?): Flow<Result<Any>> {
        return userDataSource.uploadPortrait(file).asResult().flowOn(ioDispatcher)
    }
}