package mx.dev1.pokedex.presentation.pokedexinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.dev1.pokedex.R
import mx.dev1.pokedex.core.domain.Pokemon

class PokemonsAdapter(private val items: MutableList<Pokemon>): RecyclerView.Adapter<PokemonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.pokemon_button_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Pokemon) {

        }
    }
}