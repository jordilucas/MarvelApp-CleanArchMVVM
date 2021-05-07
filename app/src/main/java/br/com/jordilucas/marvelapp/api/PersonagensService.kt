package br.com.jordilucas.marvelapp.api

import br.com.jordilucas.marvelapp.model.PersonagensResponse
import br.com.jordilucas.marvelapp.model.RevistaResponse
import br.com.jordilucas.marvelapp.model.Revistas
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonagensService {

    @GET("characters")
    fun allCharacters(@Query("offset") offset: Int? = 0) : Deferred<Response<PersonagensResponse>>

    @GET("characters/{characterId}/comics")
    fun verRevista(@Path("characterId") id: String): Deferred<Response<RevistaResponse>>

}