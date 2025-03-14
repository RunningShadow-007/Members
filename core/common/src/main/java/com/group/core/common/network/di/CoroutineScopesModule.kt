package com.group.core.common.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.annotation.AnnotationRetention.RUNTIME


/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/13 21:48<br>
 * Desc: <br>
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineScopesModule {
    @Provides
    @Singleton
    @ApplicationScope
    fun provideCoroutineScopes(@Dispatcher(MDispatchers.Default) dispatcher: CoroutineDispatcher) =
        CoroutineScope(SupervisorJob() + dispatcher)
}


@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val mDispatcher: MDispatchers)

enum class MDispatchers {
    Default,
    IO,
}