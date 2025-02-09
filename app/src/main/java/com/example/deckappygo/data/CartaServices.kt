package com.example.deckappygo.data

import android.content.Context
import android.util.Log
import com.example.deckappygo.model.CartaModel
import com.example.deckappygo.model.CartasCollectionModel
import com.example.deckappygo.model.FavoritosCartas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CartaServices {
    companion object {
        val BASE_URL = "https://db.ygoprodeck.com/api/v7/"

        private val db = FirebaseFirestore.getInstance()
        private lateinit var firebaseAuth: FirebaseAuth

        fun getCartas(context: Context): CartasCollectionModel {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiEndpoint = retrofit.create(CartasAPI::class.java)
            var result = apiEndpoint.getCartas().execute()


            return if (result.isSuccessful) {
                Log.d("debug", "Resultado Exitoso")
                result.body()!!
            } else {
                Log.e("debug", "Error al obtener las cartas")
                return CartasCollectionModel(data = ArrayList<CartaModel>())
            }
        }

        fun getCarta(name: String): ArrayList<CartasCollectionModel> {

            return ArrayList()

        }

        suspend fun guardarFavoritos(context: Context, carta: CartaModel){
            firebaseAuth = FirebaseAuth.getInstance()
            val firebaseUser = firebaseAuth.currentUser
            val email : String = firebaseUser?.email!!
            Log.d("PRUEBA",email);

            db.collection("usuarios")
                .document(email)
                .collection("favoritos")
                .document(carta.id.toString())
                .set(
                    hashMapOf(
                        "id" to carta.id,
//                        "uid" to FavoritosCartas.uuid,
                        "name" to carta.name,
                        "type" to carta.type,
                        "attribute" to carta.attribute,
                        "race" to carta.race,
                        "level" to carta.level,
                        "atk" to carta.atk,
                        "def" to carta.def,
                        "desc" to carta.desc,
                        "favorito" to true,

                    )
                )
        }

        suspend fun eliminarFavoritos(context: Context, carta: CartaModel){
            firebaseAuth = FirebaseAuth.getInstance()
            val firebaseUser = firebaseAuth.currentUser
            val email : String = firebaseUser?.email!!
            Log.d("PRUEBA",email);

            db.collection("usuarios")
                .document(email)
                .collection("favoritos")
                .document(carta.id.toString())
                .delete()
                .addOnSuccessListener {
                    Log.d("prueba","ApiService: Carta eliminada de favoritos")
                }
                .addOnFailureListener {
                    Log.d("prueba","ApiService: ERROR: La carta no se pudo eliminar de favoritos")
                }
        }

        suspend fun cartaFavoritos(context: Context): ArrayList<CartaModel>? {
            firebaseAuth = FirebaseAuth.getInstance()
            val firebaseUser = firebaseAuth.currentUser
            val email : String = firebaseUser?.email!!
            var cartas = ArrayList<CartaModel>()


            db.collection("usuarios").document(email).collection("favoritos")
                .get()
                .addOnSuccessListener { documents ->
                    if (documents != null) {
                        for (document in documents) {
                            Log.d("GetFirebase", "${document.id} => ${document.data}")
                            var carta = document.toObject(CartaModel::class.java)
                            Log.d("GetFirebase", "Convertido a objeto: => ${carta}")
                            cartas.add(carta)
                        }
                    } else {
                        Log.d("GetFirebase", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("GetFirebase", "Get failed with ", exception)
                }

            return cartas
        }

    }
}