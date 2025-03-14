package com.group.core.network.di

import androidx.tracing.trace
import com.google.gson.GsonBuilder
import com.group.core.network.BuildConfig
import com.group.core.network.datasource.user.RemoteUserDataSource
import com.group.core.network.datasource.user.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/12 17:48<br>
 * Desc: <br>
 */
@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {
    companion object {
        private val serviceMap by lazy {
            mutableMapOf<String, Any>()
        }
        private const val HOST = "http://123.57.210.143:8080/"
        private const val READ_TIMEOUT = 20000L
        private const val CONNECT_TIMEOUT = 15000L
        private const val WRITE_TIMEOUT = 20000L
        private const val CALL_TIMEOUT = 30000L

        @Provides
        @Singleton
        fun okHttpClient(): OkHttpClient = trace("MembersOkHttpClient") {
            OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .callTimeout(CALL_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .apply {
                            if (BuildConfig.DEBUG) {
                                setLevel(HttpLoggingInterceptor.Level.BODY)
                            }
                        },
                )
                .build()
        }

        @Provides
        @Singleton
        fun retrofit(): Retrofit = trace("MembersRetrofit") {
            val gson = GsonBuilder().setLenient().create()
            Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient())
                .build()
        }

        inline fun <reified T> Retrofit.provideService(clazz: Class<T>): T {
            var t: T? = null
            t = if (serviceMap[clazz.simpleName] != null) {
                serviceMap[clazz.simpleName] as T
            } else {
                create(clazz)
            }
            return t!!
        }

    }


    @Binds
    @Singleton
    abstract fun bindUserDataSource(remoteUserDataSource: RemoteUserDataSource): UserDataSource
}
