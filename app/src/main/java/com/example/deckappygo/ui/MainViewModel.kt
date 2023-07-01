package com.example.deckappygo.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deckappygo.data.CartaServices
import com.example.deckappygo.model.CartasCollectionModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {

    //Constante
    private val coroutineContext: CoroutineContext = newSingleThreadContext("Main")
    private val scope = CoroutineScope(coroutineContext)
    private var cartasCol = CartasCollectionModel(data = ArrayList())

    //Dependencias
    private val cartasServ = CartaServices

    //Propiedades
    var cartas = MutableLiveData<ArrayList<CartasCollectionModel>>()
    var names = ""

    //Funciones
    fun onStart(){
        //Cargar los datos
        scope.launch{

            kotlin.runCatching {
                cartasServ.getCarta(names)
            }.onSuccess {
                Log.d("debug", "onSuccess")
                cartas.postValue(it)
            }.onFailure {
                Log.e("debug", "Error: " + it)
            }


            //cartas = CartaServices.getCartas(this@BusquedaActivity)!!

            withContext(Dispatchers.Main){

                Log.d("debug","Cant Cartas: " + (cartasCol!!.data.size ))
                var cartitas = cartasCol!!.data[0]

                for (carta in cartasCol!!.data){

                    Log.d("debug","Carta:" + carta.name)

                }

            }
        }
    }

}