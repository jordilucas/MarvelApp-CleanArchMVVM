package br.com.jordilucas.marvelapp.ui.detalhespersonagens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.jordilucas.marvelapp.R
import br.com.jordilucas.marvelapp.extensions.loadImage
import br.com.jordilucas.marvelapp.model.Personagens
import br.com.jordilucas.marvelapp.model.Revistas
import br.com.jordilucas.marvelapp.ui.listapersonagens.PersonagensViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detalhe_personagens.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetalhesPersonagens: Fragment() {

    private val personagensViewModel: PersonagensViewModel by viewModel()

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
        with(personagensViewModel){
            verRevistas().observe(viewLifecycleOwner, Observer(::carregarDadosRevista))
            //failure().observe(viewLifecycleOwner, Observer())
            //loading().observe(viewLifecycleOwner, Observer())
        }

        verRevista.setOnClickListener { verRevistaMaisCara() }

    }

    private fun configuraToolbar(){
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(toolbar)
        activity?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        activity?.supportActionBar?.setHomeButtonEnabled(true)
        toolbar.setNavigationOnClickListener { activity.onBackPressed() }
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
    }

    private fun carregarDadosRevista(revistas: List<Revistas>){
        println(revistas)
        var maiorValor = 0.0
        var url = ""
        var position = 0
        while (revistas.listIterator().hasNext()){
            if(position < revistas.size) {
                for (rev in revistas[position].prices) {
                    if (rev.price > maiorValor) {
                        maiorValor = rev.price
                    }
                    position++
                }
            }
        }

        precoRevista.text = getString(R.string.preco_revista, maiorValor.toString())

        thumbnail.loadImage("${personagem.thumbnail.path}/landscape_large." +
                "${personagem.thumbnail.extension}")
        //nomePersonagemDetalhe.text = getString(R.string.nome, revistas.title)
        if(personagem.description.isNullOrBlank()){
            descricao.text = resources.getText(R.string.nao_tem_descricao)
        }
        else{
           // descricao.text = revistas.description
        }



    }



}