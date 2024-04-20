package com.sandesh.acharya

import retrofit2.http.GET

interface ApiService {
    @GET("content/misc/media-coverages?limit=100")
    suspend fun getPhotos(): List<MediaCoverageItem>
}