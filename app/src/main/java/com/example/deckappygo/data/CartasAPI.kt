package com.example.deckappygo.data

import com.example.deckappygo.model.CartasCollectionModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CartasAPI {

    @GET("cardinfo.php?")
    fun getCartas(
        //@Query("name") name: String
    ) : Call<CartasCollectionModel>

}