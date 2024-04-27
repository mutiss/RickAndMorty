package com.mutissx.rickmorty.base.domain.common

import com.mutissx.rickmorty.base.api.ErrorParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase {
    suspend fun <T> invoke(
        errorParser: ErrorParser,
        call: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(call.invoke())
            } catch (throwable: Throwable) {
                Resource.Error(errorParser.parseError(throwable).message.toString())
            }
        }
    }
}