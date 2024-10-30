package com.example.estore.utils

import android.content.Context
import android.content.Intent
import com.example.estore.BuildConfig
import com.example.estore.utils.constants.TimeoutConstant
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtil {
    const val BASE_URL = "https://fakestoreapi.com"
    fun buildClient(applicationContext: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val builder = OkHttpClient.Builder()
        builder.connectTimeout(TimeoutConstant.OKHTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        builder.readTimeout(TimeoutConstant.OKHTTP_READ_TIMEOUT, TimeUnit.SECONDS)
        builder.writeTimeout(TimeoutConstant.OKHTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
        builder.addInterceptor(httpLoggingInterceptor)
        builder.addInterceptor{ chain: Interceptor.Chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            if(!response.isSuccessful){
                if(response.code == 401){
                    // Handle Error
                }
            }
            response
        }
        return builder.build()
    }

    inline fun <reified T> buildService(baseUrl: String, okHttpClient: OkHttpClient): T {
        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .setVersion(1.0)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()

        return retrofit.create(T::class.java)
    }
}