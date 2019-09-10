package com.example.mvvmapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapplication.R
import com.example.mvvmapplication.data.db.entities.User
import com.example.mvvmapplication.databinding.ActivityLoginBinding
import com.example.mvvmapplication.databinding.ActivitySignupBinding
import com.example.mvvmapplication.ui.home.HomeActivity
import com.example.mvvmapplication.ui.util.hide
import com.example.mvvmapplication.ui.util.show
import com.example.mvvmapplication.ui.util.snackbar
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)


        val binding : ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)//di pa tapos 15: 16 ng youtube
        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer{user->
            if (user!=null){
                Intent(this, HomeActivity::class.java).also{
                    it.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })
    }

    override fun onStarted() {
        signupProgressBar.show()
    }

    override fun onSuccess(user: User) {
        signupProgressBar.hide()
        root_layout.snackbar("${user.name} , you are now registered")
    }

    override fun onFailure(message: String) {
        signupProgressBar.hide()
        root_layout.snackbar(message)
    }


}