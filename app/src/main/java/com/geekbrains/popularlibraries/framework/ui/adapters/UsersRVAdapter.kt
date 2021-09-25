package com.geekbrains.popularlibraries.framework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.popularlibrares.databinding.FragmentMainRecyclerItemBinding
import com.geekbrains.popularlibraries.model.entites.GithubUser

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            FragmentMainRecyclerItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(val vb: FragmentMainRecyclerItemBinding) :
        RecyclerView.ViewHolder(vb.root), UserItemView {

        override fun setLogin(text: String) {
            vb.tvLogin.text = text
        }

        override var pos = -1

    }
}
