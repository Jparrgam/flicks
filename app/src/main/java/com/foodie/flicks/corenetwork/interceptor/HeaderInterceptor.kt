package com.foodie.flicks.corenetwork.interceptor

import com.foodie.flicks.BuildConfig
import com.foodie.flicks.corenetwork.NetworkingModule.HeaderApiKey
import com.foodie.flicks.corenetwork.NetworkingModule.HeaderHost
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader(
                HeaderApiKey,
                BuildConfig.XRapidApiKey
            )
            .addHeader(
                HeaderHost,
                BuildConfig.XRapidApiHost
            )
            .build()

        return chain.proceed(request)
    }
}