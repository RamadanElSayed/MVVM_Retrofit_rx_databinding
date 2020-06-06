package com.kotlin.mvvm_retrofit_rx_databinding.adapter
import com.kotlin.mvvm_retrofit_rx_databinding.model.Data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvm_retrofit_rx_databinding.R
import com.kotlin.mvvm_retrofit_rx_databinding.databinding.UserBinding
import com.kotlin.mvvm_retrofit_rx_databinding.viewmodel.UserViewModel

class UserAdapter(private val userList: ArrayList<UserViewModel>) :

    RecyclerView.Adapter<UserAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.Holder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val userBinding: UserBinding = DataBindingUtil
            .inflate(layoutInflater, R.layout.user_item_layout, parent, false)

        return Holder(userBinding)
    }

    override fun onBindViewHolder(holder: UserAdapter.Holder, position: Int) {
        val userViewModel = userList[position]
        holder.bindItem(userViewModel)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class Holder(private val userBinding: UserBinding) : RecyclerView.ViewHolder(userBinding.root) {

        fun bindItem(userViewModel: UserViewModel) {
            this.userBinding.viewModel = userViewModel
            userBinding.executePendingBindings()
        }

    }
}