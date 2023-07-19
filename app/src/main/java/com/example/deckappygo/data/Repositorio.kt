package com.example.deckappygo.data

import android.content.Context
import com.example.deckappygo.model.CartaModel
<<<<<<< Updated upstream
=======
import com.example.deckappygo.model.FavoritosCartas
>>>>>>> Stashed changes

class Repositorio {

    companion object {

<<<<<<< Updated upstream
        suspend fun guardarFavoritos(context: Context, carta: CartaModel){
            CartaServices.guardarFavoritos(context,carta)
        }

        suspend fun eliminarFavoritos(context: Context, carta: CartaModel){
=======
        suspend fun guardarFavoritos(context: Context, carta: FavoritosCartas){
            CartaServices.guardarFavoritos(context,carta)
        }

        suspend fun eliminarFavoritos(context: Context, carta: FavoritosCartas){
>>>>>>> Stashed changes
            CartaServices.eliminarFavoritos(context,carta)
        }

        suspend fun cartaFavoritos(context: Context): ArrayList<CartaModel>? {
            return CartaServices.cartaFavoritos(context)
        }


    }
}