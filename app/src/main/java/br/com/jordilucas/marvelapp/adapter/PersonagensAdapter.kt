package br.com.jordilucas.marvelapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.jordilucas.marvelapp.R
import br.com.jordilucas.marvelapp.model.Personagens

class PersonagensAdapter(val onItemClick: (Personagens) -> Unit = {}, cxt: Context) : RecyclerView.Adapter<PersonagensViewHolder>() {
    val context = cxt

    internal var listaPersonagens = emptyList<Personagens>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonagensViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_lista_personagens,
            parent,
            false
        )
        return PersonagensViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonagensViewHolder, position: Int) {
        holder.bind(listaPersonagens[position], onItemClick, context)
    }

    override fun getItemCount() = listaPersonagens.size

}