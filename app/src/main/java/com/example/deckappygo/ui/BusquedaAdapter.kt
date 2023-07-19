package com.example.deckappygo.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deckappygo.R
import com.example.deckappygo.model.CartaModel


class BusquedaAdapter(var cartas: MutableList<CartaModel>, context: Context) : RecyclerView.Adapter<BusquedaViewHolder>() {

    var onItemClick : ( (CartaModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusquedaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.busqueda_item, parent, false)
        return BusquedaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartas.size
    }

    override fun onBindViewHolder(holder: BusquedaViewHolder, position: Int) {
       holder.name.text = cartas[position].name

        holder.itemView.setOnClickListener{
            Log.d("PRUEBA","Prueba descripcion")
            onItemClick?.invoke(cartas[position])
        }

           Glide.with(holder.itemView.context)
               .load(cartas[position].card_images.first().image_url)
               .placeholder(R.drawable.carta_carta_elegida)
               .into(holder.img)
    }

    fun update(nuevasCartas: ArrayList<CartaModel>){
        cartas = nuevasCartas
        this.notifyDataSetChanged()
    }

//    fun filterCardsByName(cartaABuscar: String){
//
//        try {
//        var filtradas = cartas.filter { it.name.contains(cartaABuscar , ignoreCase = true) }
//        Log.d("Debug","Cartas filtradas: "+filtradas.size.toString())
//        update(filtradas.toMutableList())// some code
//        } catch (e: Error) {
//            // handler
//            Log.e("Error",e.toString())
//        }
//    }

}