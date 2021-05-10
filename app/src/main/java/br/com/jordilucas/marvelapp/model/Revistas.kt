package br.com.jordilucas.marvelapp.model

import java.io.Serializable

data class Revistas(
     val title: String,
     val description: String,
     val prices: List<Prices>,
     val images: List<Images>
) : Serializable