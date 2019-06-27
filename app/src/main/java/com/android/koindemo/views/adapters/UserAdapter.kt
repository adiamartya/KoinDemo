package com.android.koindemo.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.koindemo.BR
import com.android.koindemo.R
import com.android.koindemo.databinding.ItemUserBinding
import com.android.koindemo.models.local.User

class UserAdapter(private val users: ArrayList<User>, private val clickListener: View.OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemUserBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user, parent, false)
        binding.clickListener = clickListener
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as UserViewHolder) {
            binding.setVariable(BR.user, users[position])
        }
    }

    class UserViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}