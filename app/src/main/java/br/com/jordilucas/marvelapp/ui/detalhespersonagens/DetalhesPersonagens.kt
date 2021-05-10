package br.com.jordilucas.marvelapp.ui.detalhespersonagens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.jordilucas.marvelapp.R
import br.com.jordilucas.marvelapp.extensions.loadImage
import br.com.jordilucas.marvelapp.extensions.navigate
import br.com.jordilucas.marvelapp.model.Personagens
import br.com.jordilucas.marvelapp.model.Revistas
import br.com.jordilucas.marvelapp.ui.listapersonagens.PersonagensViewModel
import br.com.jordilucas.marvelapp.ui.revista.RevistaFragmentArgs
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detalhe_personagens.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetalhesPersonagens: Fragment() {

    private val personagensViewModel: PersonagensViewModel by viewModel()
    private val origemId by lazy {R.id.irDetalhesPersonagensFragment}

    private val personagem: Personagens by lazy{
        Gson().fromJson(DetalhesPersonagensArgs.fromBundle(requireArguments()).enviarPersonagens, Personagens::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalhe_personagens, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraToolbar()
        carregarDados()
        verRevista.setOnClickListener { verRevistaMaisCara() }
    }

    private fun configuraToolbar(){
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(toolbar)
        activity?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        activity?.supportActionBar?.setHomeButtonEnabled(true)
        toolbar.setNavigationOnClickListener { activity.onBackPressed() }
        toolbar.setTitle(personagem.name)
    }

    private fun carregarDados(){
        thumbnail.loadImage("${personagem.thumbnail.path}/landscape_large." +
                "${personagem.thumbnail.extension}")
        nomePersonagemDetalhe.text = getString(R.string.nome, personagem.name)
        if(personagem.description.isNullOrBlank()){
            descricao.text = resources.getText(R.string.nao_tem_descricao)
        }
        else{
            descricao.text = personagem.description
        }
    }

    private fun verRevistaMaisCara(){
        personagensViewModel.verRevista(personagem.id.toString())
        observarRevistaMaisCara()
    }

    private fun observarRevistaMaisCara(){
        with(personagensViewModel){
            verRevistas().observe(viewLifecycleOwner, Observer(::carregarDadosRevista))
            failure().observe(viewLifecycleOwner, Observer(::observerFailure))
            loading().observe(viewLifecycleOwner, Observer(::mostrarEsconderProgress))
        }
    }

    private fun carregarDadosRevista(revistas: List<Revistas>){
        val args = RevistaFragmentArgs.Builder()
            .setEnviarRevistas(Gson().toJson(revistas))
            .build()
            .toBundle()

        navigate(origemId, R.id.irParaRevistas, args)
    }

    private fun mostrarEsconderProgress(flag: Boolean?){
        val isLoading = flag ?: false
        if(isLoading){
            progress.visibility = View.VISIBLE
        }else{
            if(progress.isVisible){
                progress.visibility = View.GONE
            }
        }
    }

    private fun observerFailure(errorMsg: String?) {
        progress.visibility = View.GONE
        errorMsg?.let {
            if (it.isNotBlank()) {
                Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
            }
        }
    }

}