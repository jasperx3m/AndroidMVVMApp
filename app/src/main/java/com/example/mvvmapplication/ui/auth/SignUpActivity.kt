package com.example.mvvmapplication.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapplication.R
import com.example.mvvmapplication.data.db.entities.User
import com.example.mvvmapplication.databinding.ActivityLoginBinding
import com.example.mvvmapplication.databinding.ActivitySignupBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)


        val binding : ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this, factory)//di pa tapos 15: 16 ng youtube
    }

    override fun onStarted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}