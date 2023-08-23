package com.nguyen.doordash2

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: StoreActivity)
}