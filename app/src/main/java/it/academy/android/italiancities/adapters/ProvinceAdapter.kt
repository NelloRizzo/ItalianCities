package it.academy.android.italiancities.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.academy.android.italiancities.R
import it.academy.android.italiancities.entities.Province

fun haiFattoClick(province: Province) {
    Log.d("Funzione HaiFattoClick", province.toString())
}

typealias ProvinceClickCallback = (Province) -> Unit

/**
 * Gestore della vista di UN SOLO elemento della lista delle province.
 * Holder significa "proprietario", quindi questa classe si occupa di gestire la vista
 * di un elemento di una lista.
 *
 * @param view la vista del layout.
 * @param callback specifica una funzione esterna che esegue un'operazione quando si fa click sulla vista.
 */
class ProvinceViewHolder(view: View, private val callback: ProvinceClickCallback? = null) :
    RecyclerView.ViewHolder(view) {

    /**
     * Serve un riferimento per ogni campo previsto nella lista.
     */
    private val nameView: TextView

    // cerco la vista nel layout e la memorizzo all'interno del campo privato della classe
    init {
        nameView = view.findViewById<TextView>(R.id.name)
    }

    /**
     * Ogni volta che dovrò visualizzare una provincia potrò richiamare un metodo
     * di questa classe per farlo.
     * @param province i dati della provincia da presentare.
     */
    fun showProvince(province: Province) {
        nameView.text = province.name
        nameView.setOnClickListener { callback?.invoke(province) }
    }
}

/**
 * L'adapter per la visualizzazione dei dati all'interno della RecycleView delle province.
 * @param model i dati da presentare.
 * @param callback la routine da eseguire quando si fa click su un dato.
 */
class ProvinceAdapter(
    private val model: List<Province>,
    private val callback: ProvinceClickCallback? = null
) :
    RecyclerView.Adapter<ProvinceViewHolder>() {
    /**
     * Ha la responsabilità di creare un ViewHolder adatto a presentare i dati.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val template = LayoutInflater // gestore delle operazioni di "inflating"
            .from(parent.context) // contesto nel quale viene creato il layout
            // operazione di "ingrandimento" del layout all'interno dell'area a sua disposizione
            .inflate(R.layout.provinces_list_item, parent, false)
        return ProvinceViewHolder(template, callback)
    }

    /**
     * Restituisce il numero di elementi da presentare.
     */
    override fun getItemCount(): Int = model.size

    /**
     * Questo metodo è il responsabile della presentazione di UN elemento,
     * quello che si trova in posizione 'position' all'interno del modello dati.
     * @param position la posizione dell'elemento da presentare.
     * @param holder il ViewHolder preparato nel metodo onCreateViewHolder.
     */
    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.showProvince(model[position])
    }
}