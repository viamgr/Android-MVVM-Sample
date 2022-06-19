package com.mvvmexample.nearbyplaces.data.di

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthenticateInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url: HttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("key", "nt0WpoWFNPf7PxPWE2qcHgjtthK73CPY")
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url).header("referer", "https://developer.tomtom.com/")

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
