package com.example.deckappygo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.deckappygo.R
import com.example.deckappygo.data.Repositorio
import com.example.deckappygo.model.FavoritosCartas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CartaActivity : AppCompatActivity() {

    private val coroutineContext: CoroutineContext = newSingleThreadContext("Descripcion")
//    private val scope = CoroutineScope(coroutineContext)

    //Botones
    lateinit var btnAtras: Button
    lateinit var btnInicio: View
    lateinit var txtInicio: TextView
    lateinit var btnBusqueda: View
    lateinit var txtBusqueda: TextView
    lateinit var btnMiDeck: View
    lateinit var txtMiDeck: TextView

    //Info


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carta)


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
    }

    override fun onStart() {
        super.onStart()

        //Boton Favoritos
        var fav = findViewById<Switch>(R.id.switch1);
//        fav.isChecked = intent.extras?.getBoolean("favorito") == true;

        //Recibir datos de l pantalla anterior
        var id = findViewById<TextView>(R.id.txtID)
        id.text = intent.extras?.getInt("ID").toString()

        var nombre = findViewById<TextView>(R.id.txtNombreCarta)
        nombre.text = intent.extras?.getString("nombre")

        var imagencarta = findViewById<ImageView>(R.id.imgCartaElegida)
        var imagen = intent.extras?.getString("imagenUrl")

        var efecto = findViewById<TextView>(R.id.txtMosEfecto)
        efecto.text = intent.extras?.getString("efecto")

        var atrib = findViewById<TextView>(R.id.txtMosAtrib)
        atrib.text = intent.extras?.getString("atributo")

        var tipo = findViewById<TextView>(R.id.txtMosTipo)
        tipo.text = intent.extras?.getString("tipo")

        var nivel = findViewById<TextView>(R.id.txtMosNivel)
        nivel.text = intent.extras?.getLong("nivel").toString()

        var atk = findViewById<TextView>(R.id.txtMosAtk)
        atk.text = intent.extras?.getLong("atk").toString()

        var def = findViewById<TextView>(R.id.txtMosDef)
        def.text = intent.extras?.getLong("def").toString()

        var desc = findViewById<TextView>(R.id.txtContenido)
        desc.text = intent.extras?.getString("descripcion")

        Glide.with(this)
            .load(imagen)
            .placeholder(R.drawable.animacion_carga)
            .centerCrop()
            .into(imagencarta)


        //Accion del boton favoritos
        fav.setOnCheckedChangeListener { _, isChecked ->

            var carta = FavoritosCartas()
            carta.id = intent.extras?.getInt("ID")
            carta.uuid = intent.extras?.getString("UUID")

            carta.name = intent.extras?.getString("nombre")

            carta.type = intent.extras?.getString("efecto")
            carta.attribute = intent.extras?.getString("atributo")
            carta.race = intent.extras?.getString("tipo")
            carta.level = intent.extras?.getLong("nivel")
            carta.atk = intent.extras?.getLong("atk")
            carta.def = intent.extras?.getLong("def")
            carta.desc = intent.extras?.getString("descripcion")

            carta.favorite = intent.extras?.getBoolean("favorito") == true

            CoroutineScope(Dispatchers.IO).launch {
                val yaEsFavorito = Repositorio.estaEnFavoritos(this@CartaActivity, carta.name ?: "")
                withContext(Dispatchers.Main) {
                    fav.isChecked = yaEsFavorito
                }
            }


            fav.setOnCheckedChangeListener { _, isChecked ->
                CoroutineScope(Dispatchers.IO).launch {
                    if (isChecked) {
                        Repositorio.guardarFavoritos(this@CartaActivity, carta)
                        withContext(Dispatchers.Main) {
                            Log.d("prueba", "Se GUARDO la carta favorita")
                            Toast.makeText(
                                this@CartaActivity,
                                "Carta Agregada a Favoritos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Repositorio.eliminarFavoritos(this@CartaActivity, carta)
                        withContext(Dispatchers.Main) {
                            Log.d("prueba", "Se ELIMINO la carta favorita")
                            Toast.makeText(
                                this@CartaActivity,
                                "Carta Eliminada de Favoritos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

    }

}
