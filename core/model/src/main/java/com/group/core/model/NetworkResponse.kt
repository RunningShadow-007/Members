package com.group.core.model

import androidx.annotation.IntDef
import kotlinx.serialization.Serializable

/**
 * Copyright:Members
 * Author: liyang <br>
 * Date:2025/3/13 13:30<br>
 * Desc: <br>
 */
const val SUCCESS = 1
const val ERROR = 0

@IntDef(SUCCESS, ERROR)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
annotation class CODE

@Serializable
class NetworkResponse<T>(@CODE val code: Int, val message: String? = "", val data: T?)

fun <T> NetworkResponse<T>.isSuccess(): Boolean {
    return code == SUCCESS
}

fun <T> NetworkResponse<T>.isError(): Boolean {
    return code == ERROR
}