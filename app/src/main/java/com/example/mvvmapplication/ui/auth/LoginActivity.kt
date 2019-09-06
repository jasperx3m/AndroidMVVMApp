package com.example.mvvmapplication.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapplication.R
import com.example.mvvmapplication.data.db.entities.User
import com.example.mvvmapplication.databinding.ActivityLoginBinding
import com.example.mvvmapplication.ui.util.hide
import com.example.mvvmapplication.ui.util.show
import com.example.mvvmapplication.ui.util.snackbar
import com.example.mvvmapplication.ui.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

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
        toast("fail") // message
    }
}
