package br.com.jordilucas.marvelapp.ui.revista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.jordilucas.marvelapp.R
import br.com.jordilucas.marvelapp.extensions.loadImage
import br.com.jordilucas.marvelapp.model.Personagens
import br.com.jordilucas.marvelapp.model.Revistas
import br.com.jordilucas.marvelapp.model.prices
import br.com.jordilucas.marvelapp.ui.detalhespersonagens.DetalhesPersonagensArgs
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detalhe_personagens.*
import java.util.*

class RevistaFragment : Fragment() {

    private val revistas: List<Revistas> by lazy{
        Gson().fromJson(RevistaFragmentArgs.fromBundle(requireArguments()).enviarRevistas, Array<Revistas>::class.java).toList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_revista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println(revistas)
        carregarDadosRevista(revistas)

    }

    private fun carregarDadosRevista(revistas: List<Revistas>){
        var maiorValor = 0.0
        for (rev in revistas){
            for(rev2 in rev.prices){
                if (rev2.price > maiorValor) {
                    maiorValor = rev2.price
                }
            }
        }

        println("Maior valor é "+maiorValor)

        //precoRevista.text = getString(R.string.preco_revista, maiorValor.toString())

        /*thumbnail.loadImage("${personagem.thumbnail.path}/landscape_large." +
                "${personagem.thumbnail.extension}")
        //nomePersonagemDetalhe.text = getString(R.string.nome, revistas.title)
        if(personagem.description.isNullOrBlank()){
            descricao.text = resources.getText(R.string.nao_tem_descricao)
        }
        else{
            // descricao.text = revistas.description
        }*/

    }

    fun findMax(list: List<Double>): Double? {
        return list.max()
    }

}