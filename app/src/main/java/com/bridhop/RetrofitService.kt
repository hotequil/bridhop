package com.bridhop

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("{cep}/json")
    fun getAddressByCep(@Path("cep") cep: String): Call<Cep>
}
