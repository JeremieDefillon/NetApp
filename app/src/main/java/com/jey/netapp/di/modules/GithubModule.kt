package com.jey.netapp.di.modules


import com.jey.netapp.di.scopes.UserScope
import com.jey.netapp.network.interfaces.GithubService

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class GithubModule {

    @Provides
    @UserScope
    fun providesGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }
}
