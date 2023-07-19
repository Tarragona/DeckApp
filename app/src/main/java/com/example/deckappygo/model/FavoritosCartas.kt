package com.example.deckappygo.model

data class FavoritosCartas(

    var id: Int? = null,
    var uuid: String? = null,

    var name: String? = null,

    var type: String? = null,
    var attribute: String? = null,
    var race: String? = null,
    var level: Long? = null,
    var atk: Long? = null,
    var def: Long? = null,

<<<<<<< Updated upstream
    var desc: String? = null
    ,
=======
    var desc: String? = null,

>>>>>>> Stashed changes
    var favorite: Boolean = false
)
