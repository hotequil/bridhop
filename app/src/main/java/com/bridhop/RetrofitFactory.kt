package com.bridhop

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val factory: Retrofit = Retrofit.Builder().baseUrl("https://viacep.com.br/ws/").addConverterFactory(GsonConverterFactory.create()).build()

    fun retrofitService(): RetrofitService{
        return factory.create(RetrofitService::class.java)
    }
}
