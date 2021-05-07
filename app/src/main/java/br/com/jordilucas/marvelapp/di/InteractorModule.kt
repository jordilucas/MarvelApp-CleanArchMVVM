package br.com.jordilucas.marvelapp.di

import br.com.jordilucas.marvelapp.domain.personagens.PersonagensInteractor
import org.koin.dsl.module

val interactorModule = module{
    single {PersonagensInteractor(get())}
}