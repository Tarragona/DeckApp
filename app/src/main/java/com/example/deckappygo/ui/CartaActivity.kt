package com.example.deckappygo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.deckappygo.R

class CartaActivity : AppCompatActivity() {

    lateinit var btnAtras: Button
    lateinit var btnInicio: View
    lateinit var txtInicio: TextView
    lateinit var btnBusqueda: View
    lateinit var txtBusqueda: TextView
    lateinit var btnMiDeck: View
    lateinit var txtMiDeck: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carta)


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