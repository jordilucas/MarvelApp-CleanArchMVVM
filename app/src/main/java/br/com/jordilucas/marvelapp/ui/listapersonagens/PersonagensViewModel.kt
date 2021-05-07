package br.com.jordilucas.marvelapp.ui.listapersonagens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.jordilucas.marvelapp.domain.Outcome
import br.com.jordilucas.marvelapp.domain.personagens.PersonagensInteractor
import br.com.jordilucas.marvelapp.model.Characters
import br.com.jordilucas.marvelapp.model.Situacao
import kotlinx.coroutines.*

class PersonagensViewModel(
    private val charactersInteractor: PersonagensInteractor
) : ViewModel(), CoroutineScope {

    private val mainJob = ArrayList<Job>()
    override val coroutineContext = Dispatchers.Main
    private val scope: CoroutineScope = CoroutineScope(coroutineContext)

    private val loading = MutableLiveData<Boolean>()
    fun loading(): LiveData<Boolean> = loading

    private val failure = MutableLiveData<String>()
    fun failure(): LiveData<String> = failure

    private val listaPersonagens = MutableLiveData<List<Characters>>()
    fun listaPersonagens() : LiveData<List<Characters>> = listaPersonagens

    private fun toogleLoading(show : Boolean){
        loading.value = show
    }

    fun listarPersonagens(offset: Int) =
        scope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main) { toogleLoading(show = true) }
            withContext(Dispatchers.Main){
                toogleLoading(false)
                val res = charactersInteractor.invoke(offset)
                when(res){
                    is Outcome.Sucess -> {
                        when(res.data.code){
                            Situacao.OK -> {
                                listaPersonagens.value = res.data.data.results
                            }
                        }
                    }
                    is Outcome.Error -> {

                    }
                    else -> {
                        failure.value = "Serviço Indisponivel"
                    }
                }
            }
        }

}