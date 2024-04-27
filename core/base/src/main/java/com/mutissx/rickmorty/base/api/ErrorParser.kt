package com.mutissx.rickmorty.base.api

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.IOException
import java.net.UnknownHostException
import retrofit2.HttpException

/**
 * Error parser class to send messages from server
 */
class ErrorParser(val gson: Gson) {
    fun parseError(exception: Throwable): Throwable =
        when (exception) {
            is HttpException -> {
                try {
                    val errorBody = exception.response()?.errorBody()?.string()
                    val errorApi = gson.fromJson(errorBody, ApiErrorResponse::class.java)
                    val apiErrorMessage = errorApi.message
                    Exception(apiErrorMessage)
                } catch (e: java.lang.Exception) {
                    exception
                }
            }
            is IOException -> {
                Exception("No internet connection")
            }
            else -> exception
        }

    private data class ApiErrorResponse(
        @SerializedName(value = "status")
        var status: Int,
        @SerializedName(value = "error")
        var message: String
    )
}
