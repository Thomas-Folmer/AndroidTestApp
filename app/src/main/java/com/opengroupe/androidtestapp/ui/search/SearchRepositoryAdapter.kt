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

class SearchRepositoryAdapter : RecyclerView.Adapter<SearchRepositoryAdapter.ViewHolder>() {

    private var repositoriesList= ArrayList<RepoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false)
        return ViewHolder(view)
    }
    fun setRepositoryList(repositoriesList: List<RepoItem>) {
      this.repositoriesList = repositoriesList as ArrayList<RepoItem>
      notifyDataSetChanged()
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

        }
    }



}