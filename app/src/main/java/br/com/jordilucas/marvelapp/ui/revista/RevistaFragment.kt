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

    }

    /*private fun carregarDadosRevista(revistas: List<Revistas>){
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

        //precoRevista.text = getString(R.string.preco_revista, maiorValor.toString())

        thumbnail.loadImage("${personagem.thumbnail.path}/landscape_large." +
                "${personagem.thumbnail.extension}")
        //nomePersonagemDetalhe.text = getString(R.string.nome, revistas.title)
        if(personagem.description.isNullOrBlank()){
            descricao.text = resources.getText(R.string.nao_tem_descricao)
        }
        else{
            // descricao.text = revistas.description
        }

    }*/

}