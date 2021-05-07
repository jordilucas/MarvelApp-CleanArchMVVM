package br.com.jordilucas.marvelapp.domain.characters

import br.com.jordilucas.marvelapp.domain.Outcome
import br.com.jordilucas.marvelapp.model.PersonagensResponse

interface Characters {

    suspend fun listarPersonagens(offset: Int): Outcome<PersonagensResponse>

}