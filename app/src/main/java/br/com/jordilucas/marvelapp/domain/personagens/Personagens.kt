package br.com.jordilucas.marvelapp.domain.personagens

import br.com.jordilucas.marvelapp.domain.Outcome
import br.com.jordilucas.marvelapp.model.PersonagensResponse

interface Personagens {

    suspend fun listarPersonagens(offset: Int): Outcome<PersonagensResponse>

}