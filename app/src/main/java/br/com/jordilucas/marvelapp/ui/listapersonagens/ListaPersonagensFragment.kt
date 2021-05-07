package br.com.jordilucas.marvelapp.ui.listapersonagens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.jordilucas.marvelapp.R
import br.com.jordilucas.marvelapp.adapter.PersonagensAdapter
import br.com.jordilucas.marvelapp.extensions.navigate
import br.com.jordilucas.marvelapp.model.Personagens
import br.com.jordilucas.marvelapp.ui.detalhespersonagens.DetalhesPersonagens
import br.com.jordilucas.marvelapp.ui.detalhespersonagens.DetalhesPersonagensArgs
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_lista_personagens.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaPersonagensFragment : Fragment() {

    private val origemId by lazy {R.id.listarPersonagensFragment}

    private val personagensViewModel: PersonagensViewModel by viewModel()
    private val personagemAdapter by lazy { context?.let { PersonagensAdapter(::aoClicarNoPersonagem, it) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista_personagens, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personagensViewModel.listarPersonagens(0)
        with(personagensViewModel){
            listaPersonagens().observe(viewLifecycleOwner, Observer(::configurarRecyclerView))
            failure().observe(viewLifecycleOwner, Observer(::observerFailure))
            //loading().observe(viewLifecycleOwner, Observer(::loading))
        }
    }

    private fun configurarRecyclerView(listaPersonagens: List<Personagens>){
        listaPersonagens?.let {
            personagemAdapter?.listaPersonagens = it
        }
        val manager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        if(listaPersonagens.isEmpty()){
            Toast.makeText(context, "Erro ao carregar lista", Toast.LENGTH_LONG).show()
        }else{
            recyclerView.apply {
                hasFixedSize()
                layoutManager = manager
                adapter = personagemAdapter
            }
        }
    }

    private fun aoClicarNoPersonagem(personagem: Personagens){

        val args = DetalhesPersonagensArgs.Builder()
                .setEnviarPersonagens(Gson().toJson(personagem))
                .build()
                .toBundle()

        navigate(origemId, R.id.irDetalhesPersonagensFragment, args)
    }

    /*private fun loading(flag: Boolean?){
        val isLoading = flag ?: false
        if(isLoading){
            progress.visibility = View.VISIBLE
        }else{
            if(progress.isVisible){
                progress.visibility = View.GONE
            }
        }
    }*/

    private fun observerFailure(errorMsg: String?) {
        //progress.visibility = View.GONE
        errorMsg?.let {
            if (it.isNotBlank()) {
                Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
            }
        }
    }
}