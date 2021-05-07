package br.com.jordilucas.marvelapp.di

import br.com.jordilucas.marvelapp.ui.listapersonagens.PersonagensViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { PersonagensViewModel(get()) }
}