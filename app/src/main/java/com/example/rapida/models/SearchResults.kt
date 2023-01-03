package com.example.rapida.models

import java.io.Serializable


//@Serializable
data class SearhResults (
    val totalResults : Int,
    val totalPages: Int,
    val result: List<Movie>
        ): Serializable