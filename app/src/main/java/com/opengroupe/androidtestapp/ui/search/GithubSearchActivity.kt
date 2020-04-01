package com.opengroupe.androidtestapp.ui.search

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.opengroupe.androidtestapp.R
import com.opengroupe.androidtestapp.data.model.RepoItem
import com.opengroupe.androidtestapp.data.model.SearchRepositoryResponse
import com.opengroupe.androidtestapp.utils.NetworkUtils
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class GithubSearchActivity : AppCompatActivity(), SearchRepositoryMvpView {


    @Inject
    lateinit var searchRepositoryMvpPresenter: SearchRepositoryMvpPresenter<SearchRepositoryMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDependencyInjection()

        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchRepositoryMvpPresenter.onSearchRepositoryClick(getFilters(query))
                return false
            }
            override fun onQueryTextChange(s: String): Boolean {
                return  false
            }
        })
        searchRepositoryMvpPresenter.onAttach(this)

    }

    fun getFilters(query: String): Map<String, Any> {
        val map: MutableMap<String, Any> = HashMap()
        map["q"] = "${query}+topic:android"
        map["page"] = 1
        map["per_page"] = 10 //to limit items count
        return map
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = true

    override fun onFetchedRepositories(searchRepositoryResponse: SearchRepositoryResponse) {
        view_empty_result.visibility = View.GONE
        view_error.visibility = View.GONE
        searchResultRecyclerView.visibility = View.VISIBLE

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val searchRepositoryAdapter = SearchRepositoryAdapter(searchRepositoryResponse.items, object : SearchRepositoryAdapter.OnItemClickListener {
            override fun onClick(repo: RepoItem) {
            }
        })
        searchResultRecyclerView.layoutManager = linearLayoutManager
        searchResultRecyclerView.adapter = searchRepositoryAdapter
    }

    override val isNetworkConnected: Boolean
        get() = NetworkUtils.isNetworkConnected(this)

    override fun showLoading() {
        hideLoading()
        progress_bar?.visibility = View.VISIBLE
        view_welcome.visibility = View.GONE
    }

    override fun hideLoading() {
        progress_bar?.visibility = View.INVISIBLE
    }

    override fun onError(message: String?) {
        view_error.visibility = View.VISIBLE
        view_error.setText(message!!,view_error)
        view_empty_result.visibility = View.GONE
        searchResultRecyclerView.visibility = View.GONE
    }

    override fun emptyResult() {
        hideLoading()
        view_empty_result.visibility = View.VISIBLE
        view_error.visibility = View.GONE
        searchResultRecyclerView.visibility = View.GONE
    }





    override fun onDestroy() {
        searchRepositoryMvpPresenter.onDetach()
        super.onDestroy()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }


}
