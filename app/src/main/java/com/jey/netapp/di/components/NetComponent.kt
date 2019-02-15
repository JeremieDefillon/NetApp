package com.jey.netapp.di.components


import android.content.SharedPreferences
import com.jey.netapp.di.modules.AppModule
import com.jey.netapp.di.modules.NetModule

import javax.inject.Singleton

import dagger.Component
import retrofit2.Retrofit

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface NetComponent {
    // downstream components need these exposed
    fun sharedPreferences(): SharedPreferences
    fun retrofit(): Retrofit
}