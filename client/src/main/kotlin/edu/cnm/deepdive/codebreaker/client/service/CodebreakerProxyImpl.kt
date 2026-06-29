package edu.cnm.deepdive.codebreaker.client.service

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import edu.cnm.deepdive.codebreaker.client.dto.*
import edu.cnm.deepdive.codebreaker.client.web.CodebreakerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.CompletableFuture

internal class CodebreakerProxyImpl(
    private val client: OkHttpClient,
    private val moshi: Moshi,
    private val api: CodebreakerApi,
    private val scope: CoroutineScope,
): CodebreakerProxy {

    override fun startGame(game: GameRequest): CompletableFuture<GameResponse> =
         scope.future { handleResponse(api.startGame(game)) }

    override fun getGame(gameId: String): CompletableFuture<GameResponse> =
        scope.future { handleResponse(api.getGame(gameId)) }

    override fun deleteGame(gameId: String): CompletableFuture<Void?> =
        scope.future { handleResponse(api.deleteGame(gameId)) }

    override fun submitGuess(
        gameId: String,
        guess: GuessRequest
    ): CompletableFuture<GuessResponse> =
        scope.future { handleResponse(api.submitGuess(gameId, guess)) }


    override fun getGuess(
        gameId: String,
        guessId: String
    ): CompletableFuture<GuessResponse> =
        scope.future { handleResponse(api.getGuess(gameId, guessId)) }

    override fun shutdown() {
        client.dispatcher.executorService.use {
            it.shutdown()
            client.connectionPool.evictAll()
        }
    }

    private fun <T> handleResponse(response: Response<T>): T =
        if(response.isSuccessful) {
            response.body() ?: throw RuntimeException()
        } else {
            handleUnsuccessfulResponse(response)
        }

    private fun handleResponse(response: Response<Unit>): Void? =
        if(response.isSuccessful) {
            null
        } else {
            handleUnsuccessfulResponse(response)
        }

    private fun <T> handleUnsuccessfulResponse(response: Response<T>): Nothing {
        val adapter = moshi.adapter(ErrorResponse::class.java)
        val errorText = response.errorBody().toString()
       throw adapter.fromJson(errorText)?.let { ApiException(it) } ?: RuntimeException()
    }

}



