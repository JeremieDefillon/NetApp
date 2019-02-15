package com.jey.netapp.di.components

import com.jey.netapp.controllers.activities.MainActivity
import com.jey.netapp.controllers.fragments.MainFragment
import com.jey.netapp.di.modules.GithubModule
import com.jey.netapp.di.scopes.UserScope

import dagger.Component

@UserScope
@Component(dependencies = [NetComponent::class], modules = [GithubModule::class])
interface GitHubComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
}
