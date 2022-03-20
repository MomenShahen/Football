package com.momen.football.core.di

import com.momen.football.core.util.CloudConfig.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(InterceptorObj)
            .connectTimeout(50, TimeUnit.SECONDS)
            .build()
    }

    object InterceptorObj : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original: Request = chain.request()

            val request: Request = original.newBuilder()
                .method(original.method, original.body)
                .build()

            return chain.proceed(request)
        }

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        val level: HttpLoggingInterceptor.Level =
            HttpLoggingInterceptor.Level.BODY
        interceptor.setLevel(level)
        return interceptor
    }
}