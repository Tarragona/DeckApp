package com.example.deckappygo.data

import android.content.Context
import com.example.deckappygo.model.CartaModel
import com.example.deckappygo.model.FavoritosCartas

class Repositorio {

    companion object {

        suspend fun guardarFavoritos(context: Context, carta: FavoritosCartas){
            CartaServices.guardarFavoritos(context,carta)
        }

        suspend fun eliminarFavoritos(context: Context, carta: FavoritosCartas){
            CartaServices.eliminarFavoritos(context,carta)
        }

        suspend fun cartaFavoritos(context: Context): ArrayList<CartaModel>? {
            return CartaServices.cartaFavoritos(context)
        }
    }
}

