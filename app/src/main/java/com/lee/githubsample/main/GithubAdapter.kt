package com.lee.githubsample.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lee.githubsample.R
import com.lee.githubsample.data.Repository
import com.lee.githubsample.databinding.ItemRepositoryBinding

class GithubAdapter : ListAdapter<Repository, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<Repository?>() {

    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.description == newItem.description
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ItemRepositoryBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_repository,
                parent,
                false
            )
        val holder = RepositoryViewHolder(
                binding
            )

        return holder
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val repositoryViewHolder = viewHolder as RepositoryViewHolder
        return repositoryViewHolder.bind(getItem(position))
    }

    class RepositoryViewHolder(
        private val binding: ItemRepositoryBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Repository) {
            with(binding) {
                repository = data
            }
        }
    }

}
