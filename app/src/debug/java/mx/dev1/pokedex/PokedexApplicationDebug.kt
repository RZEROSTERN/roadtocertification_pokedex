package mx.dev1.pokedex

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokedexApplicationDebug : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@PokedexApplicationDebug)
            modules(appModule)
        }
    }
}