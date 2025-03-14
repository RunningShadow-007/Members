package com.group.core.network.datasource.user.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/14 11:02<br>
 * Desc: <br>
 */
@Parcelize
data class LoginRequest(
    val phone: String,
    val password: String
) : Parcelable

@Parcelize
data class RegisterRequest(
    val phone: String,
    val password: String
) : Parcelable

@Parcelize
data class LoginEntity(
    val request: LoginRequest
) : Parcelable