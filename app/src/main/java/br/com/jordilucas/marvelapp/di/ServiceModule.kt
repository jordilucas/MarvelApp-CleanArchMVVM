package br.com.jordilucas.marvelapp.di

import br.com.jordilucas.marvelapp.api.CharactersService
import br.com.jordilucas.marvelapp.api.createApi
import org.koin.dsl.module

val serviceModule = module {
    single { createApi<CharactersService>(get()) }
}