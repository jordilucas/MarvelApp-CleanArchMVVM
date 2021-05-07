package br.com.jordilucas.marvelapp.model

data class RevistaResponse(
    val code: Int,
    val status: String,
    val message: String,
    val data: RevistaData
)