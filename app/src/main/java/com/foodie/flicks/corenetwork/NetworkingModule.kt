package com.foodie.flicks.corenetwork

import com.foodie.flicks.BuildConfig
import com.foodie.flicks.corenetwork.interceptor.HeaderInterceptor
import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.daggerscopes.SingleIn
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.skydoves.sandwich.retry.RetryPolicy
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@ContributesTo(AppScope::class)
object NetworkingModule {
    const val HeaderApiKey = "X-RapidAPI-Key"
    const val HeaderHost = "X-RapidAPI-Host"

    @Provides
    @SingleIn(AppScope::class)
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(
                KotlinJsonAdapterFactory()
            )
            .build()

    @Provides
    @SingleIn(AppScope::class)
    fun provideRetrofit(
        moshi: Moshi,
        okHttpClientLazy: dagger.Lazy<OkHttpClient>,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .baseUrl(BuildConfig.BaseUrl)
        .client(okHttpClientLazy.get())
        .build()

    @Provides
    @SingleIn(AppScope::class)
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                    redactHeader(HeaderApiKey)
                    redactHeader(HeaderHost)
                }
            )
            .addInterceptor(
                HeaderInterceptor()
            )
            .build()

    @Provides
    @SingleIn(AppScope::class)
    fun provideRetryPolicy(): RetryPolicy =
        object : RetryPolicy {
            override fun shouldRetry(attempt: Int, message: String?): Boolean = attempt <= 1

            override fun retryTimeout(attempt: Int, message: String?): Int = 3000
        }
}