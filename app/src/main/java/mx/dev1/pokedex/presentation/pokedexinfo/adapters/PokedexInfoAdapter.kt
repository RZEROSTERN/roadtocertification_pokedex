package mx.dev1.pokedex.presentation.pokedexinfo.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.dev1.pokedex.R
import mx.dev1.pokedex.core.domain.Pokemon
import java.util.*

class PokedexInfoAdapter(private val items: MutableList<Pokemon>): RecyclerView.Adapter<PokedexInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.pokemon_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val pokemonName: TextView = itemView.findViewById(R.id.txt_pokemon_name)
        private val pokemonSprite: ImageView = itemView.findViewById(R.id.iv_pokemon_sprite)

        @ExperimentalStdlibApi
        fun bind(item: Pokemon) {
            Glide.with(itemView).load(item.pokemonDetails.sprites.front_default).into(pokemonSprite)
            pokemonName.text = item.pokemonSpecies.name.capitalize(Locale.getDefault())
        }
    }
}