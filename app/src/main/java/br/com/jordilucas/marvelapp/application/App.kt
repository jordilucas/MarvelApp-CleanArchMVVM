package br.com.jordilucas.marvelapp.application

import android.app.Application
import br.com.jordilucas.marvelapp.di.appMarvelComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appMarvelComponent)
        }

    }

}