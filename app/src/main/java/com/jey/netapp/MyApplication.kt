package com.jey.netapp

import android.app.Application
import com.jey.netapp.di.components.DaggerGitHubComponent
import com.jey.netapp.di.components.DaggerNetComponent

import com.jey.netapp.di.components.GitHubComponent
import com.jey.netapp.di.components.NetComponent
import com.jey.netapp.di.modules.AppModule
import com.jey.netapp.di.modules.GithubModule
import com.jey.netapp.di.modules.NetModule

class MyApplication : Application() {

    lateinit var netComponent: NetComponent
    lateinit var gitHubComponent: GitHubComponent

    override fun onCreate() {
        super.onCreate()

        // specify the full namespace of the component
        // Dagger_xxxx (where xxxx = component name)
        netComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("https://api.github.com"))
                .build()

        gitHubComponent = DaggerGitHubComponent.builder()
                .netComponent(netComponent)
                .githubModule(GithubModule())
                .build()

    }
}