package com.kotlin.mvvm_retrofit_rx_databinding.network

import com.kotlin.mvvm_retrofit_rx_databinding.model.User
import io.reactivex.Observable

class CreateRepository(val api: ApiService) {

    fun data(): Observable<User> {
        return api.getData()
    }
}


 object CreateRepositoryProvider {

    var apiService: ApiService = Factor.create().create(ApiService::class.java)
    fun provideCreateRepository(): CreateRepository {
        return CreateRepository(apiService)
    }

}