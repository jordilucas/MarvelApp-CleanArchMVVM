package br.com.jordilucas.marvelapp.ui.revista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.jordilucas.marvelapp.R
import br.com.jordilucas.marvelapp.extensions.loadImage
import br.com.jordilucas.marvelapp.model.Revistas
import br.com.jordilucas.marvelapp.model.Images
import br.com.jordilucas.marvelapp.model.Prices
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_revista.*
import kotlinx.android.synthetic.main.fragment_revista.toolbar
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

        configuraToolbar()
        carregarDadosRevista(revistas)

    }

    private fun configuraToolbar(){
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(toolbar)
        activity?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        activity?.supportActionBar?.setHomeButtonEnabled(true)
        toolbar.setNavigationOnClickListener { activity.onBackPressed() }
    }

    private fun carregarDadosRevista(revistas: List<Revistas>){
        var maiorValor = 0.0
        var image: Images
        var indexRevista: Int = -1
        for (rev in revistas){
            for(rev2 in rev.prices){
                if (rev2.price > maiorValor) {
                    maiorValor = rev2.price
                    indexRevista = revistas.indexOf(rev)
                }
            }
        }

        toolbar.setTitle(revistas.get(indexRevista).title)

        println("Maior valor é "+maiorValor)
        println("Index "+indexRevista.toString())

        preco.text = getString(R.string.preco_revista, maiorValor.toString())
        if(revistas.get(indexRevista)?.description.isNullOrBlank()){
            descricao.text = resources.getText(R.string.nao_tem_descricao)
        }
        else{
             descricao.text = revistas[0].description
        }

        thumbnail.loadImage("${revistas[0].images[0].path}/landscape_large." +
                "${revistas[indexRevista].images[indexRevista].extension}")
    }

}