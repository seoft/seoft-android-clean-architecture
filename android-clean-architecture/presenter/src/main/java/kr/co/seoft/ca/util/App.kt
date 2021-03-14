package kr.co.seoft.ca.util

import android.app.Application
import com.facebook.stetho.Stetho
import kr.co.seoft.ca.BuildConfig
import kr.co.seoft.ca.di.resultModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(resultModule)
        }

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

    }

}