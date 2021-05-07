package br.com.jordilucas.marvelapp.domain.characters

import br.com.jordilucas.marvelapp.domain.Outcome
import br.com.jordilucas.marvelapp.model.PersonagensResponse
import java.io.IOException

class CharactersInteractor(
    private val personagens: Characters
) {

    suspend fun invoke(offset: Int): Outcome<PersonagensResponse>{
        val response = personagens.listarPersonagens(offset)
        if(response is Outcome.Sucess){
            return response
        }
        return Outcome.Error(IOException())
    }

}