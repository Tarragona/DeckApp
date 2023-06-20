package com.example.deckappygo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.deckappygo.R
import com.example.deckappygo.data.CartaServices
import com.example.deckappygo.model.Welcome
import com.example.deckappygo.model.CartasCollectionModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class BusquedaActivity : AppCompatActivity()  {

    lateinit var btnAtras: Button
    lateinit var btnInicio: View
    lateinit var txtInicio: TextView
    lateinit var btnBusqueda: View
    lateinit var txtBusqueda: TextView
    lateinit var btnMiDeck: View
    lateinit var txtMiDeck: TextView
    private val coroutineContext: CoroutineContext = newSingleThreadContext("Main")
    private val scope = CoroutineScope(coroutineContext)
    private var cartas = CartasCollectionModel(data = ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_busqueda)




            scope.launch{

                cartas = CartaServices.getCartas(this@BusquedaActivity)!!

                //var ejererciciosfavoritos = Repos.fetchFavoritos()

                withContext(Dispatchers.Main){
                    //Log.d("debug","cant Personajes: " + personajes!!.data.size)
                    Log.d("debug","Cant Cartas: " + (cartas!!.data.size ))
                    var cartitas = cartas!!.data[0]

                    for (carta in cartas!!.data){

                        Log.d("debug","Carta:" + carta.name)

                    }

                }
            }





        //El boton Back vuelve a la pantalla anterior
        btnAtras = findViewById(R.id.btnAtras)
        btnAtras.setOnClickListener {
            finish()
            onBackPressed()
        }

        //Al presionar los botones de la lowbar hacer...
        //Boton o texto de inicio
        btnInicio = findViewById(R.id.icono_home)
        btnInicio.setOnClickListener {
            val intent: Intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }

        txtInicio = findViewById(R.id.txtInicio)
        txtInicio.setOnClickListener {
            val intent: Intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }

        //Boton o texto de Busqueda
        btnBusqueda = findViewById(R.id.icono_busqueda)
        btnBusqueda.setOnClickListener {
            val intent: Intent = Intent(this, BusquedaActivity::class.java)
            startActivity(intent)
        }

        txtBusqueda = findViewById(R.id.txtBusqueda)
        txtBusqueda.setOnClickListener {
            val intent: Intent = Intent(this, BusquedaActivity::class.java)
            startActivity(intent)
        }

        //Boton o texto de Mi Deck
        btnMiDeck = findViewById(R.id.icono_favoritos)
        btnMiDeck.setOnClickListener {
            val intent: Intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }

        txtMiDeck = findViewById(R.id.txtMiDeck)
        txtMiDeck.setOnClickListener {
            val intent: Intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }
    }

}