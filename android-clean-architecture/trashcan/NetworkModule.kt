package kr.co.seoft.ca.data.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    private val gsonFactory by lazy {
        GsonConverterFactory.create(GsonBuilder().create())
    }

    private fun getRetrofit(endpointURL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpointURL)
            .client(httpClient)
            .addConverterFactory(gsonFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    fun createLoginApi(endpointURL: String): LoginApi {
        val retrofit = getRetrofit(endpointURL)
        return retrofit.create(LoginApi::class.java)
    }

}