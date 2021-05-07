package br.com.jordilucas.marvelapp.ui.detalhespersonagens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.jordilucas.marvelapp.R
import br.com.jordilucas.marvelapp.model.Personagens
import com.google.gson.Gson

class DetalhesPersonagens: Fragment() {

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

}