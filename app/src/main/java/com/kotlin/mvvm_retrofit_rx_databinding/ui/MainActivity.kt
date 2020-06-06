package com.kotlin.mvvm_retrofit_rx_databinding.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.mvvm_retrofit_rx_databinding.R
import com.kotlin.mvvm_retrofit_rx_databinding.adapter.UserAdapter
import com.kotlin.mvvm_retrofit_rx_databinding.model.User
import com.kotlin.mvvm_retrofit_rx_databinding.network.CreateRepositoryProvider
import com.kotlin.mvvm_retrofit_rx_databinding.viewmodel.UserViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var userViewModel: UserViewModel? = null

    private var adapter: UserAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository=CreateRepositoryProvider.provideCreateRepository()
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        compositeDisposable.add(
            repository.data()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        handleResponse(it)
                    }, { error ->
                        error.printStackTrace()
                        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                    }
                )


        )
    }

    private fun handleResponse(it: User?) {
        userViewModel!!.getData(it!!).observe(this, Observer { data ->
            adapter = UserAdapter(data)
            recyclerview.layoutManager = LinearLayoutManager(this)

            recyclerview.adapter = adapter
        })

    }
}
