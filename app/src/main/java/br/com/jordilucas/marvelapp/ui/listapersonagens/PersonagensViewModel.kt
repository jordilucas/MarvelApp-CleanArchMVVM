package br.com.jordilucas.marvelapp.ui.listapersonagens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.jordilucas.marvelapp.domain.characters.CharactersInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class PersonagensViewModel(
    private val charactersInteractor: CharactersInteractor
) : ViewModel(), CoroutineScope {

    private val mainJob = ArrayList<Job>()

    override val coroutineContext = Dispatchers.Main

    private val scope: CoroutineScope = CoroutineScope(coroutineContext)

    private val loading = MutableLiveData<Boolean>()
    fun loading(): LiveData<Boolean> = loading

}