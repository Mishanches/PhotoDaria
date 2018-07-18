package ru.nb.mish.photostudioex.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

// конфигурируем http-клиент
object ServiceGenerator {

    private var BASE_URL = "http://soccer.stage-1st.ru/player/" // базовый сервер

    private val httpClient = OkHttpClient.Builder() // кофнигурируем http-запрос
            .connectTimeout(10, TimeUnit.SECONDS) // коннектимся 10 секунд
            .readTimeout(10, TimeUnit.SECONDS) // читаем данные тоже 10 секунд
            .writeTimeout(10, TimeUnit.SECONDS) // записываем данные тоже 10 секунд
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // расширенные логи

    // библтотека доступа к сети
    val retrofit = Retrofit.Builder() // создаем retrofit-клиент, который создает запросы к серверу
            .client(httpClient.build()) // клиент для http-запроса
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create( // используем Jackson для конертации объектов json с сервера
                    ObjectMapper().registerModule(SimpleModule())
            ))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())  // для ассинхронных запросов к серверу
            .build()

    val serverApi: ServerApi // делаем запросы и вызываем метод закгрузки
        get() = retrofit.create(ServerApi::class.java) // делаем get-апросы с помощью ретрофит
}