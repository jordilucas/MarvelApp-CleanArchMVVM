package br.com.jordilucas.marvelapp.api

import br.com.jordilucas.marvelapp.model.Characters
import br.com.jordilucas.marvelapp.model.PersonagensResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonagensService {

    @GET("characters")
    fun allCharacters(@Query("offset") offset: Int? = 0) : Deferred<Response<PersonagensResponse>>

}