package com.example.nasabrowser

import android.app.Application
import com.example.nasabrowser.core.ServiceLocator

class NasaViwerApplication : Application() {
    val serviceLocator = ServiceLocator()
}