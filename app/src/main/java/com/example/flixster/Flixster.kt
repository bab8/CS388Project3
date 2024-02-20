package com.example.flixster

import com.google.gson.annotations.SerializedName

class Flixster {

    @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("poster_path")
    var movieImageUrl: String? = null

    @SerializedName("overview")
    var description: String? = null

    @SerializedName("release_date")
    var release: String? = null

    @SerializedName("vote_count")
    var votes: String? = null

    @SerializedName("vote_average")
    var rating: String? = null



}