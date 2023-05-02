package com.bridhop

import com.google.gson.annotations.SerializedName

data class Cep (
    @SerializedName("cep") val zipcode: String = "",
    @SerializedName("logradouro") val street: String  = "",
    @SerializedName("complemento") val complement: String  = "",
    @SerializedName("bairro") val district: String  = "",
    @SerializedName("localidade") val location: String  = "",
    @SerializedName("uf") val state: String  = "",
    @SerializedName("unidade") val unit: String  = "",
    val ibge: String  = "",
    val gia: String  = ""
)
