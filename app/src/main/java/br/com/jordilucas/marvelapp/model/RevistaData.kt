package br.com.jordilucas.marvelapp.model

data class RevistaData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Revistas>
)