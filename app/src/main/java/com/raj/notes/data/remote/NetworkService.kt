package com.raj.notes.data.remote

import com.raj.notes.data.remote.Endpoints
import com.raj.notes.data.remote.Networking
import com.raj.notes.data.remote.request.LoginRequest
import com.raj.notes.data.remote.request.SignUpRequest
import com.raj.notes.data.remote.response.LoginResponse
import com.raj.notes.data.remote.response.PostListResponse
import com.raj.notes.data.remote.response.SignUpResponse
import com.raj.notes.data.remote.request.PostLikeModifyRequest
import com.raj.notes.data.remote.response.GeneralResponse
import io.reactivex.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface NetworkService {



    /*
     * Example to add other headers
     *
     *  @POST(Endpoints.DUMMY_PROTECTED)
        fun sampleDummyProtectedCall(
            @Body request: DummyRequest,
            @Header(Networking.HEADER_USER_ID) userId: String, // pass using UserRepository
            @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String, // pass using UserRepository
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
        ): Single<DummyResponse>
     */

    @POST(Endpoints.LOGIN)
    fun doLoginCall(
        @Body request: LoginRequest,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<LoginResponse>




    @POST(Endpoints.SIGNUP)
    fun doSignUpCall(
        @Body request: SignUpRequest,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<SignUpResponse>


    @GET(Endpoints.HOME_POSTS_LIST)
    fun doHomePostListCall(
        @Query("firstPostId") firstPostId: String?,
        @Query("lastPostId") lastPostId: String?,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<PostListResponse>

    @PUT(Endpoints.POST_LIKE)
    fun doPostLikeCall(
        @Body request: PostLikeModifyRequest,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<GeneralResponse>

    @PUT(Endpoints.POST_UNLIKE)
    fun doPostUnlikeCall(
        @Body request: PostLikeModifyRequest,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<GeneralResponse>
}