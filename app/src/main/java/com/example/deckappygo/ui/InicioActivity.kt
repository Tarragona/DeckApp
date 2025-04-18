package com.example.deckappygo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deckappygo.R
import com.example.deckappygo.data.CartaServices
import com.example.deckappygo.model.CartaModel
import com.example.deckappygo.model.CartasCollectionModel
import com.firebase.ui.auth.AuthUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class InicioActivity : AppCompatActivity() {

    lateinit var btn_logout:Button
    lateinit var pb_logout: ProgressBar
    lateinit var btnInicio: View
    lateinit var txtInicio: TextView
    lateinit var btnBusqueda: View
    lateinit var txtBusqueda: TextView
    lateinit var btnMiDeck: View
    lateinit var txtMiDeck: TextView
    private lateinit var rvCartasInicio: RecyclerView

    private val coroutineContext: CoroutineContext = newSingleThreadContext("Main")
    private val scope = CoroutineScope(coroutineContext)
    private var cartas = CartasCollectionModel(data = ArrayList())
    private var nombreCartas = ArrayList<String>()
    private lateinit var adapter : BusquedaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        signOut()

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
            val intent: Intent = Intent(this, InicioActivity::class.java)
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
            val intent: Intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
        }

        rvCartasInicio = findViewById<RecyclerView>(R.id.rvCartasInicio)
        rvCartasInicio.layoutManager = LinearLayoutManager(this)
        adapter = BusquedaAdapter(cartas!!.data,this)
        rvCartasInicio.adapter = adapter

        scope.launch{
            cartas = CartaServices.getCartas(this@InicioActivity)!!
            withContext(Dispatchers.Main){
                Log.d("debug","Cant Cartas: " + (cartas!!.data.size ))
                var cartitas = cartas!!.data[0]
                for (carta in cartas!!.data){

                    nombreCartas.add(carta.name)
                }
                adapter.update(cartas!!.data)
            }
        }

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

    private fun signOut() {

        btn_logout = findViewById(R.id.btn_logout)
        pb_logout = findViewById(R.id.pb_logout)

        btn_logout.setOnClickListener{
            pb_logout.visibility = View.VISIBLE
            btn_logout.isEnabled = false
            AuthUI.getInstance().signOut(this).addOnSuccessListener {
                startActivity(Intent(this,LoginActivity::class.java))
                Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener {
                btn_logout.isEnabled = true
                pb_logout.visibility = View.GONE
                Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}