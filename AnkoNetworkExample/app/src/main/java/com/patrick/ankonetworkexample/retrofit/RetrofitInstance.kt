package com.patrick.ankonetworkexample.retrofit

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 네트워크 통신을 담당할 인스턴스 생성
const val INSTAGRAM_API_URL = "https://api.instagram.com"
const val MEDIA_ID = ""
const val ACCESS_TOKEN = ""

val okHttpClient = OkHttpClient.Builder()
    .readTimeout(1500, TimeUnit.MILLISECONDS)
    .build()

val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .baseUrl(INSTAGRAM_API_URL)
    .build()

data class InstagramLoginResponse(var value: String)

data class InstagramPostsResponse(var value: String)

class InstagramCommentsResponse(
    var data: List<DataItem?>? = null,
    var meta: Meta? = null)

data class DataItem(var createdTime: String?,
                    var from: From?,
                    var id: String,
                    var text: String?)

data class Meta(var code: Int?)

data class From(var fullname: String?,
                var profilePicture: String?,
                var id: String?,
                var username: String?)

val instagramApiService = retrofit.create<InstagramApiService>(InstagramApiService::class.java)

fun getComments() {
    instagramApiService.getCommentsForInstagramPost(MEDIA_ID, ACCESS_TOKEN)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe( { res -> Log.i("Comments", res.toString()) }, { t: Throwable -> Log.e("ERROR", t.message) } )
}

