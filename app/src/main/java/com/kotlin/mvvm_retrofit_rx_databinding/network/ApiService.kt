package com.kotlin.mvvm_retrofit_rx_databinding.network

import com.kotlin.mvvm_retrofit_rx_databinding.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("api/users?page=2")
    fun getData(): Observable<User>
}