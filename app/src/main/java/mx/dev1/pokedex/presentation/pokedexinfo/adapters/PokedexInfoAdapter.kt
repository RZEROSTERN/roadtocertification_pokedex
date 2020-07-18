package mx.dev1.pokedex.presentation.pokedexinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.dev1.pokedex.R
import mx.dev1.pokedex.core.domain.Pokemon
import mx.dev1.pokedex.presentation.region.adapters.PokedexesAdapter

class PokedexInfoAdapter(private val items: MutableList<Pokemon>): RecyclerView.Adapter<PokedexInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexInfoAdapter.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.pokemon_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PokedexInfoAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val pokemonName: TextView = itemView.findViewById(R.id.txt_pokemon_name)

        fun bind(item: Pokemon) {
            pokemonName.text = item.pokemonSpecies.name.capitalize()
        }
    }
}