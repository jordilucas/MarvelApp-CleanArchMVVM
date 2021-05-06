package br.com.jordilucas.marvelapp.api

import br.com.jordilucas.marvelapp.extensions.toMd5
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*

private const val OKHTTP_TIMEOUT = 60L

fun createOkhttpClient(): OkHttpClient{

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
    client.addInterceptor(logging)
    client.addInterceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url()

        val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", "$ts$PRIVATE_KEY$API_KEY".toMd5())
            .build()
        chain.proceed(original.newBuilder().url(url).build())
    }
    return client.build()
}

fun createretrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(okHttpClient)
    .build()

inline fun <reified T> createApi(retrofit: Retrofit): T = retrofit.create(T::class.java)
