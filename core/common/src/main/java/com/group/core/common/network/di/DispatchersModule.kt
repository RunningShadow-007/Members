package com.group.core.common.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/13 21:57<br>
 * Desc: <br>
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Dispatcher(MDispatchers.Default)
    fun provideDefaultDispatchers(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Dispatcher(MDispatchers.IO)
    fun provideIODispatchers(): CoroutineDispatcher = Dispatchers.IO

}