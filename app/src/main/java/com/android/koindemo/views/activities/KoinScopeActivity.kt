package com.android.koindemo.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.android.koindemo.R
import com.android.koindemo.databinding.ActivityKoinScopeBinding
import com.android.koindemo.models.local.User
import com.android.koindemo.viewmodels.KoinViewModel
import com.android.koindemo.views.adapters.UserAdapter
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class KoinScopeActivity : AppCompatActivity(), Observer<List<User>> {

    private lateinit var binding: ActivityKoinScopeBinding
    //private val viewModel: KoinViewModel by currentScope.viewModel(this, named("vm")) //get viewmodel by lazy
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_koin_scope)
        binding.rvUser.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //viewModel.getUsers().observe(this, this) //------------------------------------> viewmodel without any scope
        //currentScope.get<KoinViewModel>(named<KoinScopeActivity>()).getUsers().observe(this, this) //---------> viewmodel in currentScope i.e current activity

        /**
         * create custom scope
         */
        val scope = getKoin().createScope("id", named<KoinScopeActivity>())
        val viewModel = scope.get<KoinViewModel>(named<KoinScopeActivity>())
        viewModel.getUsers()
            .observe(this, this) //--------> viewmodel in custom scope

        scope.close() //----------> close scope when no more in use. If you are using activity scope then it will be closed automatically when activity is destroyed
    }

    override fun onChanged(users: List<User>?) {
        users?.apply {
            if (size > 0)
                binding.rvUser.adapter = UserAdapter(users as ArrayList<User>, clickListener)
            else
                Toast.makeText(this@KoinScopeActivity, "No users are there in database", Toast.LENGTH_LONG).show()
        }
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.llUser -> {
                val intent = Intent(this, KoinFragmentActivity::class.java)
                startActivity(intent.run { putExtra(USER, view.tag as? User) })
            }
        }
    }
}