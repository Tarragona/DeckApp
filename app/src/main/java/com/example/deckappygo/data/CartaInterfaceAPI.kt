package com.example.deckappygo.data
import com.example.deckappygo.model.Welcome
import com.example.deckappygo.model.CartasCollectionModel
import retrofit2.Call
import retrofit2.http.GET

interface CartaInterfaceAPI {

//    @GET("/api/v2/exercise?limit=500")
    @GET("cardinfo.php?")
    fun getCartas(
    ) : Call<CartasCollectionModel>

}