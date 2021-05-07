package br.com.jordilucas.marvelapp.domain.personagens

import br.com.jordilucas.marvelapp.api.PersonagensService
import br.com.jordilucas.marvelapp.domain.Outcome
import br.com.jordilucas.marvelapp.model.PersonagensResponse
import br.com.jordilucas.marvelapp.model.Revistas
import java.io.IOException

class PersonagensInteractor(
    private val personagens: PersonagensService
) {

    suspend fun invoke(offset: Int): Outcome<PersonagensResponse>{
        val response = personagens.allCharacters(offset).await()
        if(response.isSuccessful){
            val body = response.body()
            if(body != null){
                return Outcome.Sucess(body)
            }
        }
        return Outcome.Error(IOException())
    }

    suspend fun invoke(id: String): Outcome<Revistas>{
        val response = personagens.verRevista(id).await()
        if(response.isSuccessful){
            val body = response.body()
            if(body != null){
                return Outcome.Sucess(body)
            }
        }
        return Outcome.Error(IOException())
    }

}