package br.com.jordilucas.marvelapp.model

import java.io.Serializable

data class Revistas(
     val title: String,
     val description: String,
     val prices: List<prices>,
     val images: List<images>
) : Serializable