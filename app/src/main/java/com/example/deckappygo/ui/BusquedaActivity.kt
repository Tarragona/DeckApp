package com.example.deckappygo.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deckappygo.R
import com.example.deckappygo.data.CartaServices
import com.example.deckappygo.model.CartaModel
import com.example.deckappygo.model.CartasCollectionModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.math.log

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
    private var nombreCartas = ArrayList<String>()
    private lateinit var rvBusq: RecyclerView
    private lateinit var svBusqueda: TextInputEditText
//    private  lateinit var viewModel: MainViewModel
    private lateinit var adapter : BusquedaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_busqueda)
        rvBusq = findViewById<RecyclerView>(R.id.rvBusqueda)
        svBusqueda = findViewById<TextInputEditText>(R.id.svBusqueda)
        rvBusq.layoutManager = LinearLayoutManager(this)
        adapter = BusquedaAdapter(cartas!!.data,this)
        rvBusq.adapter = adapter


        adapter.onItemClick = { carta: CartaModel ->
            val intent = Intent(this, CartaActivity::class.java)
            intent.putExtra("nombre", carta.name)
            intent.putExtra("imagenUrl", carta.card_images.first().image_url_small)
            startActivity(intent)
            finish()
        }


        scope.launch{
            cartas = CartaServices.getCartas(this@BusquedaActivity)!!
            withContext(Dispatchers.Main){
                Log.d("debug","Cant Cartas: " + (cartas!!.data.size ))
                var cartitas = cartas!!.data[0]
                for (carta in cartas!!.data){

                    nombreCartas.add(carta.name)
                }
                adapter.update(cartas!!.data)
            }
        }


//        NO CREO QUE VAYA
//        bindView()
//        bindViewModel()

//        svBusqueda.setOnQueryTextListener(
//            object : SearchView.OnQueryTextListener {
//                override fun onQueryTextChange(newText: String): Boolean {
//                    Log.d("debug",newText)
//                    return true
//                }
//
//                override fun onQueryTextSubmit(query: String): Boolean {
//                    adapter.filterCardsByName(query)
//                    return true
//                }
//            }
//
//        )



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
    override fun onStart(){
        super.onStart()

        var searchInput = findViewById<TextInputEditText>(R.id.svBusqueda)
        // filtro
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(search: CharSequence?, start: Int, before: Int, count: Int) {
                val ejercicios = buscarEjerciciosPorNombre(cartas.data, search);
                updateRecipesQuery(ejercicios)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

            private fun buscarEjerciciosPorNombre(cartas: ArrayList<CartaModel>, search: CharSequence?): ArrayList<CartaModel> {
                val cartasEncontradas = ArrayList<CartaModel>();
                for (carta in cartas) {
                    if (carta.name?.uppercase()?.contains(search.toString().uppercase()) == true) {
                        cartasEncontradas.add(carta)
                    }
                }
                return cartasEncontradas
            }

            private fun updateRecipesQuery(cartas: ArrayList<CartaModel>) {
                scope.launch {
                    withContext(Dispatchers.Main) {
                        adapter.update(cartas)
                    }
                }
            }

        })


    }



//      NO CREO QUE VAYA

//    private fun bindView() {
//        rvBusq = findViewById(R.id.rvBusqueda)
//        rvBusq.layoutManager = LinearLayoutManager(this)
//    }
////
//    private fun bindViewModel() {
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
////        viewModel.cartas.observe(this){
////            //Actualizar la lista de la pantalla
////        }
//
//    }
}