package com.example.tohackmeapp

import com.example.tohackmeapp.FormatConvertion.toObject
import com.google.firebase.firestore.Exclude
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


data class User (
//    val id : String = "",
    var name : String = "",
    var monster_id : Int = 1,
    var level : Int = 1,
    var hp : Int = 10,
    var ep : Int = 0,
    var physical : Int = 0,
    var intelligence : Int = 0,
    var lifestyle : Int = 0,
    var others : Int = 0
)

data class Todo (
    var title : String = "",
    var explanation : String = "",
    var tag : String = "",
    var level : Int? = null,
    var status : Boolean = false,
    @get:Exclude
    var id : String? = null
)

data class Monster (
    val id : Int = 1,
    val name : String = "Penguin"
)

object FormatConvertion {
    val gson = Gson()
    // Convert a Map to an object
    inline fun <reified T> Map<String, Any>.toObject(): T {
        return convert()
    }

    // Convert an object to a Map
    fun <T> T.toMap(): Map<String, Any> {
        return convert()
    }

    // Convert an object of type T to type R
    inline fun <T, reified R> T.convert(): R {
        val json = gson.toJson(this)
        println(json)
        return gson.fromJson(json, object : TypeToken<R>() {}.type)
    }
}