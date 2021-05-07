package br.com.jordilucas.marvelapp.ui.listapersonagens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.jordilucas.marvelapp.domain.Outcome
import br.com.jordilucas.marvelapp.domain.personagens.PersonagensInteractor
import br.com.jordilucas.marvelapp.model.Personagens
import br.com.jordilucas.marvelapp.model.Revistas
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

    private val listaPersonagens = MutableLiveData<List<Personagens>>()
    fun listaPersonagens() : LiveData<List<Personagens>> = listaPersonagens

    private val revistas = MutableLiveData<Revistas>()
    fun verRevistas() : LiveData<Revistas> = revistas

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
                            Situacao.Invalid_Referer -> {
                                failure.value = res.data.message
                            }
                            Situacao.Missing_API_Key -> {
                                failure.value = res.data.message
                            }
                            Situacao.Missing_Forbidden -> {
                                failure.value = res.data.message
                            }
                            Situacao.Missing_Hash -> {
                                failure.value = res.data.message
                            }
                            Situacao.Missing_Invalid_Hash -> {
                                failure.value = res.data.message
                            }
                            Situacao.Missing_Method_Not_Allowed -> {
                                failure.value = res.data.message
                            }
                            Situacao.Missing_Timestamp -> {
                                failure.value = res.data.message
                            }
                        }
                    }
                    is Outcome.Error -> {
                        failure.value = "Error"
                    }
                    else -> {
                        failure.value = "Serviço Indisponivel"
                    }
                }
            }
        }

    fun verRevista(id: String) =
        scope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) { toogleLoading(show = true) }
            withContext(Dispatchers.Main) {
                toogleLoading(false)
                val res = charactersInteractor.invoke(id)
                when (res) {
                    is Outcome.Sucess -> {
                        revistas.value = res.data
                    }
                    is Outcome.Error -> {
                        failure.value = res.exception.message
                    }

                    else -> {
                    }
                }
            }
        }
}