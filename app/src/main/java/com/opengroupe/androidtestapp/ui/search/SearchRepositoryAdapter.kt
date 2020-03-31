package com.opengroupe.androidtestapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.opengroupe.androidtestapp.R
import com.opengroupe.androidtestapp.data.model.RepoItem
import kotlinx.android.synthetic.main.list_item_repo.view.*

class SearchRepositoryAdapter(private val repositoriesList: List<RepoItem>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<SearchRepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(repositoriesList[position])
    }

    override fun getItemCount() = repositoriesList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(repoItem: RepoItem) {

            itemView.name_text.text = "${repoItem.name} • ${repoItem.forksCount}"
            itemView.description_text.text = repoItem.description

            Glide.with(itemView.context).load(repoItem.owner?.avatarUrl)
                    .apply(RequestOptions().placeholder(R.drawable.ic_avatar).error(R.drawable.ic_avatar))
                    .into(itemView.avatar_image)

            itemView.setOnClickListener {
                onItemClickListener.onClick(repoItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(repo: RepoItem)
    }

}