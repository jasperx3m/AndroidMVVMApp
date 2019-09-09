package com.example.mvvmapplication.data.network

import com.example.mvvmapplication.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
const val BASE_URL="https://api.simplifiedcoding.in/course-apis/mvvm/"
interface LoginApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin( //suspend -> function that can be paused and resumed
        @Field("email") email : String,
        @Field("password") password : String
    ) : Response<AuthResponse>


    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
        @Field("name") name : String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<AuthResponse>


    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : LoginApi{

            val okHttpClient= OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(LoginApi::class.java)
        }
    }
}