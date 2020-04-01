package com.opengroupe.androidtestapp.di.module

import com.opengroupe.androidtestapp.BuildConfig
import com.opengroupe.androidtestapp.data.remote.RestApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

private const val CONNECTION_TIMEOUT_SECS: Long = 20

@Module
class NetworkModule {


    @Provides
    @Singleton
    fun provideMoshiConverterLibrary(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun providesRxJava2CallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(logging)
        }

        okHttpClientBuilder
                .connectTimeout(CONNECTION_TIMEOUT_SECS, TimeUnit.SECONDS)
                .readTimeout(CONNECTION_TIMEOUT_SECS, TimeUnit.SECONDS)
                .writeTimeout(CONNECTION_TIMEOUT_SECS, TimeUnit.SECONDS)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRestApiHelper(
            okHttpClient: OkHttpClient,
            moshiConverterFactory: MoshiConverterFactory,
            rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): RestApi {
        val builder = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(moshiConverterFactory)
        val retrofit = builder.client(okHttpClient).build()
        return retrofit.create(RestApi::class.java)
    }
}