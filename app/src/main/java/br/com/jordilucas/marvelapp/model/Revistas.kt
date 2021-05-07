package br.com.jordilucas.marvelapp.model

data class Revistas(
    private val title: String,
    private val description: String,
    private val prices: List<prices>,
    private val images: List<images>
)