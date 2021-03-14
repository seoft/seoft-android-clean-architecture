package kr.co.seoft.ca.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import kr.co.seoft.ca.BuildConfig
import kr.co.seoft.ca.data.api.ContactApi
import kr.co.seoft.ca.data.api.LoginApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { createApi<LoginApi>(get(), BuildConfig.API_HOST) }
    single { createApi<ContactApi>(get(), BuildConfig.API_HOST) }

    factory {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            if (BuildConfig.DEBUG) addNetworkInterceptor(StethoInterceptor())
            readTimeout(30L, TimeUnit.SECONDS)
            writeTimeout(30L, TimeUnit.SECONDS)
            connectTimeout(30L, TimeUnit.SECONDS)
        }.build()
    }
}

inline fun <reified T> createApi(
    okHttpClient: OkHttpClient,
    url: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
    return retrofit.create(T::class.java)
}
