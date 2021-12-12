package de.check24.girokonto.data.source.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.check24.girokonto.data.source.api.response.FirstPageResponse
import de.check24.girokonto.data.source.api.response.NullToZeroDoubleAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("first-page")
    suspend fun firstPage(): FirstPageResponse

    companion object {
        private const val BASE_URL = "https://my-json-server.typicode.com/khahani/api/"

        fun create(): Api {
            val logger = HttpLoggingInterceptor().apply {
                level = Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val moshi = Moshi.Builder()
                .add(NullToZeroDoubleAdapter)
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(Api::class.java)
        }
    }
}

