package com.jyldyzferr.composemoviesapp.di


import com.jyldyzferr.composemoviesapp.data.cloud.network.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.themoviedb.org/3/"

private val API_KEY =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZWEwYzU4Nzc5MGMyMjQ1MmZjY2ZhN2YzODk3M2UyNyIsInN1YiI6IjY0ZjZiZTkyZmZjOWRlMDExYmU4YjE4YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.eCmZKoF1P9UF8lSbZ70y_YTUro3JbrhJJSrcll_wQZ0"


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(Interceptor { chain ->
                        val request = chain.request()
                            .newBuilder()
                            .addHeader("Authorization",  "Bearer $API_KEY")
                            .build()

                        return@Interceptor chain.proceed(request = request)
                    })
                    .build()
            )
            .build()
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit ): MovieService =
        retrofit.create(MovieService::class.java)
}
