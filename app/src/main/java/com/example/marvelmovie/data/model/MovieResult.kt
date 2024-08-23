package com.example.marvelmovie.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResult(
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val poster: String,
): Parcelable
