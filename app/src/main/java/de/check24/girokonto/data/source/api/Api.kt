package de.check24.girokonto.data.source.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface Api {

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"

        fun create(): Api {
            val logger = HttpLoggingInterceptor().apply {
                level = Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}