package com.geekbrains.popularlibraries.baselogic.user_repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.popularlibrares.databinding.RepositoryItemViewBinding
import com.geekbrains.popularlibraries.model.repositories.RepositoryListPresenter
import javax.inject.Inject

class RepositoryRVAdapter @Inject constructor(
    private val presenterRepository: RepositoryListPresenter
    ): RecyclerView.Adapter<RepositoryRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(RepositoryItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .apply {
                itemView.setOnClickListener {
                    presenterRepository.itemClickListener?.invoke(this)
                }
            }

    override fun getItemCount() = presenterRepository.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenterRepository.bindView(holder.apply { currentPosition = position })

    inner class ViewHolder(private val binding: RepositoryItemViewBinding):
        RecyclerView.ViewHolder(binding.root), RepositoryItemView {

        override var currentPosition = -1

        override fun setName(text: String) {
            binding.repositoryName.text = text
        }
    }
}