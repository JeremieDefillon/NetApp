package com.jey.netapp.network.interfaces

import com.jey.netapp.models.GithubUser
import com.jey.netapp.models.GithubUserInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{username}/following")
    fun getFollowing(@Path("username")username: String): Observable<List<GithubUser>>

    @GET("/users/{username}")
    fun getUserInfos(@Path("username") username: String): Observable<GithubUserInfo>
}