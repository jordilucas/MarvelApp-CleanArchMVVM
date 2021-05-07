package br.com.jordilucas.marvelapp.di

import br.com.jordilucas.marvelapp.domain.characters.CharactersInteractor
import org.koin.dsl.module

val interactorModule = module{
    single {CharactersInteractor(get())}
}