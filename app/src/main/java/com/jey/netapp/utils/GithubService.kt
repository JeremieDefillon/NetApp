package com.jey.netapp.utils

import com.jey.netapp.models.GithubUser
import com.jey.netapp.models.GithubUserInfo

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Observable<List<GithubUser>>

    @GET("/users/{username}")
    fun getUserInfos(@Path("username") username: String): Observable<GithubUserInfo>

    companion object {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
