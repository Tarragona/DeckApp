package com.example.deckappygo.data

import android.content.Context
import android.util.Log
import com.example.deckappygo.model.Welcome
import com.example.deckappygo.model.CartasCollectionModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CartaServices {
companion object{
    //var allCards: ArrayList<CartaModel> = TODO()
    val BASE_URL = "https://db.ygoprodeck.com/api/v7/"
    fun getCartas(context: Context): CartasCollectionModel {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiEndpoint = retrofit.create(CartaInterfaceAPI::class.java)
        var result = apiEndpoint.getCartas().execute()

//        for (carta in result.body()!!){
//            allCards.add(carta)
//            Log.d("carta",carta.name);
//        }

        return if(result.isSuccessful){
            result.body()!!
        }
        else{
            Log.e("debug","Error al obtener las cartas")

            return CartasCollectionModel(data = ArrayList<Welcome>())
        }
    }
}




}