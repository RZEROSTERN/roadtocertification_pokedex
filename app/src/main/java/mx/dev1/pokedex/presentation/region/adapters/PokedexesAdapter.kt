package mx.dev1.pokedex.presentation.region.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.dev1.pokedex.R
import mx.dev1.pokedex.core.domain.Pokedex

class PokedexesAdapter(private val items: MutableList<Pokedex>, val listener: View.OnClickListener): RecyclerView.Adapter<PokedexesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.pokedex_item, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View, listener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
        private val nameText = itemView.findViewById<TextView>(R.id.txt_pokemon_name)

        init {
            itemView.setOnClickListener(listener)
        }

        fun bind(item: Pokedex) {
            nameText.text = item.name.replace("-", " ").capitalize()
        }
    }
}