package com.revature.popquiz.model.api

import retrofit2.Retrofit

/**
 * Helper class for loading from a server
 *
 * Contains call functions for the api
 */
object RetrofitHelper {

    private val retrofit: Retrofit
        get() {
            TODO()
        }

    init {
        /**
         * Create a builder for Retrofit, including:
         *
         * Base URL of the API
         * Converter Factory to convert Kotlin to JSON
         * CallAdapter to include Coroutine functionality into loading
         */
        val builder = Retrofit.Builder()
    }


}