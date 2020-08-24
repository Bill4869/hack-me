package com.example.tohackmeapp

import com.example.tohackmeapp.FormatConvertion.toObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


data class User (
    val id : String,
    val name : String,
    val monster_id : Int = 1,
    val level : Int = 1,
    val hp : Int = 10,
    val ep : Int = 0,
    val physical : Int = 0,
    val intelligence : Int = 0,
    val lifestyle : Int = 0,
    val others : Int = 0
)

data class Todo (
    val id : Int,
    val title : String,
    val explanation : String,
    val tag : String,
    val level : Int,
    val status : Boolean = false
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