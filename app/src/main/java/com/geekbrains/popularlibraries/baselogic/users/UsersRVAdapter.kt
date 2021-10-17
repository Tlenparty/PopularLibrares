package com.geekbrains.popularlibraries.baselogic.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.popularlibrares.databinding.FragmentMainRecyclerItemBinding
import com.geekbrains.popularlibraries.extentions.setStartDrawableCircleImageFromUri
import com.geekbrains.popularlibraries.framework.ui.adapters.IUserListPresenter
import com.geekbrains.popularlibraries.framework.ui.adapters.UserItemView
import com.geekbrains.popularlibraries.framework.ui.images.GlideImageLoader
import com.geekbrains.popularlibraries.model.entites.GithubUser
import com.geekbrains.popularlibraries.model.repositories.UserAvatarRepository
import javax.inject.Inject

class UsersRVAdapter constructor(
    private val presenter: IUserListPresenter,
    //private val imageLoader:GlideImageLoader
    private val userAvatarRepository: UserAvatarRepository
) :
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
            currentPosition = position
        })

    override fun getItemCount(): Int = presenter.getCount()

    // Благодаря inner можем доступаться к внешним полям UsersRVAdapter
    inner class ViewHolder(val vb: FragmentMainRecyclerItemBinding) :
        RecyclerView.ViewHolder(vb.root), UserItemView {

        override fun setLogin(text: String) {
            vb.tvLogin.text = text
        }

        override fun setAvatar(user: GithubUser) {
            vb.tvLogin.setStartDrawableCircleImageFromUri(userAvatarRepository.imageBuilder(user))
            //    imageLoader.load(url,vb.tvAvatar)
        }

        override var currentPosition = -1

    }
}
