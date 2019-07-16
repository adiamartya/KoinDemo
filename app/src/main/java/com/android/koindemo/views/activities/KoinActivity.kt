package com.android.koindemo.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.koindemo.R
import com.android.koindemo.databinding.ActivityKoinBinding
import com.android.koindemo.models.local.User
import com.android.koindemo.viewmodels.KoinViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class KoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKoinBinding
    private val viewModel: KoinViewModel by viewModel()
    //private lateinit var viewModel: KoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_koin)
        /*val scope = getKoin().run { getScopeOrNull("id") ?: createScope("id", named<KoinScopeActivity>()) }
        viewModel = scope.getViewModel(this, named<KoinScopeActivity>())
        Log.d("ViewModel Id", viewModel.toString())*/
        initToolbar()
        binding.clicklistener = clickListener
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setHomeButtonEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.buttonAdd -> insertUser()

            R.id.buttonView -> startActivity(Intent(this, KoinScopeActivity::class.java))
        }
    }

    private fun insertUser() {
        val email = binding.etEmail.text.toString()
        val name = binding.etName.text.toString()
        val phone = binding.etPhone.text.toString()
        if (email.isNotBlank() && name.isNotBlank() && phone.isNotBlank()) {
            val user = binding.run {
                User(
                    etEmail.text.toString(),
                    etName.text.toString(), etPhone.text.toString()
                )
            }
            viewModel.insertUser(user)
            binding.apply {
                etEmail.text.clear()
                etName.text.clear()
                etPhone.text.clear()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}