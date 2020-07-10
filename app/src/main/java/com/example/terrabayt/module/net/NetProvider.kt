package com.example.module.common

import com.example.module.data.net.NetService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetProvider() {
    companion object {
        private var instance: NetService? = null

        fun instance(baseUrl: String = "https://www.terabayt.uz/"): NetService {
            if (instance == null) {
                instance = Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .client(okhttpclient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NetService::class.java)
            }
            return instance!!
        }

        private fun okhttpclient(): OkHttpClient {
            return OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        fun clear() {
            instance = null
        }
    }
}