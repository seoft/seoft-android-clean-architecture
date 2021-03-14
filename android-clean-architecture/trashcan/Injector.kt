package kr.co.seoft.ca.di

import android.content.Context
import kr.co.seoft.ca.BuildConfig
import kr.co.seoft.ca.data.api.NetworkModule
import kr.co.seoft.ca.data.mapper.LoginMapper
import kr.co.seoft.ca.data.repository.login.LoginRemoteDataSourceImpl
import kr.co.seoft.ca.data.repository.login.LoginRepositoryImpl


object Injector {

    private val networkModule by lazy { NetworkModule() }

    @Volatile
    var loginRepository: LoginRepositoryImpl? = null

    fun provideLoginRepository(context: Context): LoginRepositoryImpl {
        return loginRepository ?: createLoginRepository(context).apply { loginRepository = this }
    }

    private fun createLoginRepository(context: Context): LoginRepositoryImpl {
        return LoginRepositoryImpl(
            LoginRemoteDataSourceImpl(
                networkModule.createLoginApi(BuildConfig.API_HOST), LoginMapper()
            )
        )
    }

}