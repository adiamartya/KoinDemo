package com.android.koindemo.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.koindemo.R
import com.android.koindemo.databinding.FragmentKoinBinding
import com.android.koindemo.models.local.User
import com.android.koindemo.models.local.UserDetails
import com.android.koindemo.viewmodels.UserDetailsViewModel
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.qualifier.named

const val ARGS_USER = "user"

class KoinFragment : Fragment(), Observer<UserDetails> {
    private lateinit var binding: FragmentKoinBinding

    companion object {
        fun getInstance(user: User): Fragment {
            val fragment = KoinFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARGS_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_koin, container, false)
        val user = arguments?.get(ARGS_USER) as? User
        //val scope = getKoin().createScope("frag", named("fragScope")) //----------> Custom scope inside fragment
        user?.email?.let {
            activity?.currentScope?.getViewModel<UserDetailsViewModel>(this, named<KoinFragment>())?.getUserDetails(it)
                ?.observe(this, this) //-------->using activity's currentScope

            /*scope.getViewModel<UserDetailsViewModel>(this).getUserDetails(it).observe(this, this)
            scope.close()*/ //----> using custom scope to fetch data from viewmodel

            //getSharedViewModel<UserDetailsViewModel>().getUserDetails(it).observe(this, this) //---> used when you want to share data between activity and fragment
        }
        Toast.makeText(activity, getSharedViewModel<UserDetailsViewModel>().name, Toast.LENGTH_LONG)
            .show() //----> name has been set in KoinFragmentActivity. To get same instance of viewmodel that has been used in activity, don't use custom scope or currentScope or getViewModel. This all will give new instances of viewmodel.
        return binding.root
    }

    override fun onChanged(userDetails: UserDetails?) {
        userDetails?.apply { binding.text = userDetails.aboutUser }
    }
}