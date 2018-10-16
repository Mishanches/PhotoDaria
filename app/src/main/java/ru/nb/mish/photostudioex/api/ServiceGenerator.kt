package ru.nb.mish.photostudioex.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private var BASE_URL = "http://soccer.stage-1st.ru/player/"

    private val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

    val retrofit = Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create(
                    ObjectMapper().registerModule(SimpleModule())
            ))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    val serverApi: ServerApi
        get() = retrofit.create(ServerApi::class.java)
}