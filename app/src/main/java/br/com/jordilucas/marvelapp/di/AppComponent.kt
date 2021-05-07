package br.com.jordilucas.marvelapp.di

val appMarvelComponent by lazy {
    listOf(
        remoteModule,
        interactorModule,
        serviceModule,
        uiModule
    )
}