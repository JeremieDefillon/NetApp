package com.jey.netapp.utils

import com.jey.netapp.models.GithubUser
import com.jey.netapp.models.GithubUserInfo
import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object GithubStreams {

    fun streamFetchUserFollowing(username: String): Observable<List<GithubUser>> {
        val gitHubService = GithubService.retrofit.create<GithubService>(GithubService::class.java!!)
        return gitHubService.getFollowing(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)
    }

    fun streamFetchUserInfos(username: String): Observable<GithubUserInfo> {
        val gitHubService = GithubService.retrofit.create<GithubService>(GithubService::class.java!!)
        return gitHubService.getUserInfos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)
    }

    fun streamFetchUserFollowingAndFetchFirstUserInfos(username: String): Observable<GithubUserInfo> {
        return streamFetchUserFollowing(username) // 1 - Fetch all users that user follows
                .map { users ->
                    users[0] // 2 - Return the user with the most followers
                }
                .flatMap { user ->
                    streamFetchUserInfos(user.login!!) // 3 - Get all repos for this user
                }
    }
}

