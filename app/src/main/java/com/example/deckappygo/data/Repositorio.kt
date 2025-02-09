package com.example.deckappygo.data

import android.content.Context
import com.example.deckappygo.model.CartaModel


class Repositorio {

    companion object {

        suspend fun guardarFavoritos(context: Context, carta: CartaModel){
            CartaServices.guardarFavoritos(context,carta)
        }

        suspend fun eliminarFavoritos(context: Context, carta: CartaModel){
            CartaServices.eliminarFavoritos(context,carta)
        }

        suspend fun cartaFavoritos(context: Context): ArrayList<CartaModel>? {
            return CartaServices.cartaFavoritos(context)
        }


    }
}