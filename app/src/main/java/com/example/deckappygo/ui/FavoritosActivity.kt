package com.example.deckappygo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deckappygo.R
import com.example.deckappygo.data.Repositorio
import com.example.deckappygo.model.CartaModel
import com.example.deckappygo.model.CartasCollectionModel
import com.example.deckappygo.model.FavoritosCartas
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FavoritosActivity : AppCompatActivity() {

    lateinit var btnAtras: Button
    lateinit var btnInicio: View
    lateinit var txtInicio: TextView
    lateinit var btnBusqueda: View
    lateinit var txtBusqueda: TextView
    lateinit var btnMiDeck: View
    lateinit var txtMiDeck: TextView
    private lateinit var rvCartasFavoritos: RecyclerView

    private lateinit var auth: FirebaseAuth

    private val coroutineContext: CoroutineContext = newSingleThreadContext("Main")
    private val scope = CoroutineScope(coroutineContext)
    private var cartas = CartasCollectionModel(data = ArrayList())
    private var nombreCartas = ArrayList<String>()
    private lateinit var adapter : BusquedaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        auth = FirebaseAuth.getInstance()

<<<<<<< Updated upstream
=======
        val id = intent.getStringExtra("ID")


>>>>>>> Stashed changes
        //El boton Back
        btnAtras = findViewById(R.id.btnAtras)
        btnAtras.setOnClickListener {
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

        rvCartasFavoritos = findViewById<RecyclerView>(R.id.rvCartasFavoritos)
        rvCartasFavoritos.layoutManager = LinearLayoutManager(this)
        adapter = BusquedaAdapter(cartas!!.data,this)
        rvCartasFavoritos.adapter = adapter

        ///////



        adapter.onItemClick = { carta: CartaModel ->
            val intent = Intent(this, CartaActivity::class.java)

            intent.putExtra("ID", carta.id)
            intent.putExtra("nombre", carta.name)
            intent.putExtra("imagenUrl", carta.card_images.first().image_url)

            intent.putExtra("efecto", carta.type)
            intent.putExtra("atributo", carta.attribute)
            intent.putExtra("tipo", carta.race)
            intent.putExtra("nivel", carta.level)
            intent.putExtra("atk", carta.atk)
            intent.putExtra("def", carta.def)

            intent.putExtra("descripcion", carta.desc)

            Log.d("debug","Info Carta:" + "ID:" + carta.id + "Nombre:" + carta.name)

            startActivity(intent)
            finish()
        }
    }
}