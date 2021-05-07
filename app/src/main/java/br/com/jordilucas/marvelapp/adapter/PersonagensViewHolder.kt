package br.com.jordilucas.marvelapp.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.jordilucas.marvelapp.extensions.loadImage
import br.com.jordilucas.marvelapp.model.Personagens
import kotlinx.android.synthetic.main.item_lista_personagens.view.*

class PersonagensViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(personagens : Personagens, onItemClick: (Personagens) -> Unit, context:Context){
        itemView.fotoPersonagem.loadImage("${personagens.thumbnail.path}/standard_medium." +
                "${personagens.thumbnail.extension}")
        itemView.nomePersonagem.text = personagens.name
        itemView.onClick.setOnClickListener {
            onItemClick(personagens)
        }
    }

}