package br.com.jordilucas.marvelapp.model

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Personagens>
)