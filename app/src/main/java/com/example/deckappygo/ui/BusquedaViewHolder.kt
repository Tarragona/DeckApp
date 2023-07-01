package com.example.deckappygo.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deckappygo.R

class BusquedaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val name: TextView = itemView.findViewById(R.id.txtNombreCartaBusqueda)
    val img: ImageView = itemView.findViewById(R.id.imgCartaBusqueda)

}