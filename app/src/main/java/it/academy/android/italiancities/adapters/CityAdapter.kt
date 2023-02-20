package it.academy.android.italiancities.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.academy.android.italiancities.R
import it.academy.android.italiancities.entities.City

class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameView: TextView
    private val acronymView: TextView

    init {
        nameView = view.findViewById(R.id.name)
        acronymView = view.findViewById(R.id.acronym)
    }

    fun showCity(city: City) {
        nameView.text = city.name
        acronymView.text = city.province?.acronym
    }
}

class CityAdapter(private val model: List<City>) : RecyclerView.Adapter<CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val template = LayoutInflater // gestore delle operazioni di "inflating"
            .from(parent.context) // contesto nel quale viene creato il layout
            // operazione di "ingrandimento" del layout all'interno dell'area a sua disposizione
            .inflate(R.layout.cities_list_item, parent, false)
        return CityViewHolder(template)
    }

    override fun getItemCount(): Int = model.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.showCity(model[position])
    }

}