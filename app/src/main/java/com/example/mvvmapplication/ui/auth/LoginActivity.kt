package com.example.mvvmapplication.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapplication.R
import com.example.mvvmapplication.data.db.AppDatabase
import com.example.mvvmapplication.data.db.entities.User
import com.example.mvvmapplication.databinding.ActivityLoginBinding
import com.example.mvvmapplication.ui.home.HomeActivity
import com.example.mvvmapplication.ui.util.hide
import com.example.mvvmapplication.ui.util.show
import com.example.mvvmapplication.ui.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = LoginApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api , db)
        val factory = AuthViewModelFactory(repository)*/


        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        viewModel.getLoggedInUser().observe(this, Observer{user ->
            if (user != null){
                Intent(this, HomeActivity::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }
        })
        binding.viewmodel= viewModel
        viewModel.authListener= this
    }
    override fun onStarted() {
        loginProgressBar.show()
    }

    override fun onSuccess(user: User) {
        loginProgressBar.hide()
        /*toast("${user.name} is Logged In")*/
        root_layout.snackbar("${user.name} is Logged In")

    }

    override fun onFailure(message: String) {
        loginProgressBar.hide()
       /* toast("fail") // message*/
        root_layout.snackbar(message)
    }
}
