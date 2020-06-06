package com.kotlin.mvvm_retrofit_rx_databinding.viewmodel

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.kotlin.mvvm_retrofit_rx_databinding.model.Data
import com.kotlin.mvvm_retrofit_rx_databinding.model.User
import de.hdodenhof.circleimageview.CircleImageView

class UserViewModel : ViewModel
{
    var id: Int = 0
    var email: String = ""
    var first_name: String = ""
    var last_name: String = ""
    var avatar: String = ""
    constructor() : super()
    constructor(
       data:Data
    ) : super() {
        this.id = data.id
        this.email = data.email
        this.first_name = data.first_name
        this.last_name = data.last_name
        this.avatar = data.avatar
    }

    var arrayListMutableLiveData = MutableLiveData<ArrayList<UserViewModel>>()
    var arrayList=ArrayList<UserViewModel>()


    fun getData(user:User): MutableLiveData<ArrayList<UserViewModel>>
    {
     for (element in user.data)
     {
         arrayList.add(UserViewModel(element))
     }
        arrayListMutableLiveData.value=arrayList
        return arrayListMutableLiveData
    }
    fun getAvatarUrl(): String {
        return avatar
    }

    fun getUserName(): String {
        return "$first_name $last_name"
    }

}

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setAvatarUrl(view: CircleImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }
}

