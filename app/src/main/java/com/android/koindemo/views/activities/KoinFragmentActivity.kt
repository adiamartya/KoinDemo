package com.android.koindemo.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.koindemo.R
import com.android.koindemo.databinding.ActivityContainerBinding
import com.android.koindemo.models.local.User
import com.android.koindemo.models.local.UserDetails
import com.android.koindemo.viewmodels.UserDetailsViewModel
import com.android.koindemo.views.fragments.KoinFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

const val USER = "user"

class KoinFragmentActivity : AppCompatActivity() {
    //private val viewModel: UserDetailsViewModel by currentScope.viewModel(this, named<KoinFragment>())
    private val viewModel: UserDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityContainerBinding>(this, R.layout.activity_container)
        val user: User = intent.getParcelableExtra(USER)
        viewModel.name = user.name
        val userDetails =
            "Hello. I am ${user.name}. I am writing this blog on Koin 2.0 - pragmatic lightweight dependency injection framework for Kotlin.\nYou may contact me through email on ${user.email}"
        user.email.apply { viewModel.insertUserDetails(UserDetails(this, userDetails)) }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, KoinFragment.getInstance(user), "KoinFragment").commit()
    }
}