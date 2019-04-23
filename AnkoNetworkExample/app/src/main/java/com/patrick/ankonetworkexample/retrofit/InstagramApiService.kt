package com.patrick.ankonetworkexample.retrofit

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface InstagramApiService {
    @FormUrlEncoded
    @POST("oauth/authorize")
    fun getRedirectCode(@Field("client_id") client_id: String,
                        @Field("redirect_uri") redirect_uri: String,
                        @Field("response_type") response_type: String): Call<String>

    @FormUrlEncoded
    @POST("oauth/access_token")
    fun getAccessToken(@Field("client_id") client_id: String,
                       @Field("client_secret") client_secret: String,
                       @Field("redirect_uri") redirect_uri: String,
                       @Field("grant_type") grant_type: String,
                       @Field("code") code: String): Observable<InstagramLoginResponse>

    @GET("v1/users/{users_id}/media/recent/")
    fun getInstagramPosts(@Path("user_id") user_id: String?,
                          @Query("access_token") access_token: String?): Observable<InstagramPostsResponse>

    @GET("v1/media/{media_id}/comments")
    fun getCommentsForInstagramPost(@Path("media_id") media_id: String?,
                                    @Query("access_token") access_token: String?): Observable<InstagramCommentsResponse>

}