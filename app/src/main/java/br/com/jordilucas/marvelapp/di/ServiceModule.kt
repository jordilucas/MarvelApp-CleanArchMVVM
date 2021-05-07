package br.com.jordilucas.marvelapp.di

import br.com.jordilucas.marvelapp.api.PersonagensService
import br.com.jordilucas.marvelapp.api.createApi
import org.koin.dsl.module

val serviceModule = module {
    single { createApi<PersonagensService>(get()) }
}