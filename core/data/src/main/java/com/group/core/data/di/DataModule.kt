package com.group.core.data.di

import com.group.core.data.repository.UserDataRepository
import com.group.core.data.repository.UserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/14 14:44<br>
 * Desc: <br>
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindUserDataSource(userDataRepositoryImpl: UserDataRepositoryImpl): UserDataRepository
}