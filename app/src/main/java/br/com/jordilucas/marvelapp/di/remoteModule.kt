package br.com.jordilucas.marvelapp.di

import br.com.jordilucas.marvelapp.api.createOkhttpClient
import br.com.jordilucas.marvelapp.api.createretrofit
import org.koin.dsl.module

val remoteModule = module {
    single { createOkhttpClient() }
    single { createretrofit(get()) }
}