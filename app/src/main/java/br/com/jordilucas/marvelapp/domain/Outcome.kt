package br.com.jordilucas.marvelapp.domain

import java.lang.Exception

sealed class Outcome<out T : Any> {

    data class Sucess<out T : Any>(val data: T) : Outcome<T>()
    data class Error(val exception: Exception, val httpCode: Int = -500) : Outcome<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Sucess<*> -> "Sucess[data=$data]"
            is Error -> "Error[Exception=$exception ($httpCode)]"
        }
    }

}