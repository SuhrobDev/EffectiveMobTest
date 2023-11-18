package dev.soul.data.common

import dev.soul.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

open class ResultHandler {
    protected suspend fun <T, K> loadResult(
        requestSource: suspend () -> Response<T>,
        successBody: suspend (T, FlowCollector<Resource<K>>) -> Unit
    ): Flow<Resource<K>> = flow {
        try {
            emit(Resource.Loading())
            val response = requestSource.invoke()
            if (response.isSuccessful) {
                val result = response.body()
                if (result != null) {
                    successBody.invoke(result, this)
                } else {
                    emit(
                        Resource.Error(
                            "An expected error occurred!"
                        )
                    )
                }
            } else {
                emit(
                    Resource.Error(
                        response.message()
                    )
                )
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    "An expected error occurred!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Couldn't reach server. Please check your internet connection!"
                )
            )
        }
    }
}