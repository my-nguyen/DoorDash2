package com.nguyen.doordash2

import android.app.Application

class MyApplication: Application() {
    val appComponent = DaggerApplicationComponent.create()
}