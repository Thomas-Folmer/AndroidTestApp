package com.opengroupe.androidtestapp.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
                if (!search_view.isIconified) {
                    search_view.isIconified = true
                }
                searchRepositoryMvpPresenter.onSearchRepositoryClick(query,1,"","desc",10)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean = false
        })
        searchRepositoryMvpPresenter.onAttach(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = true

    override fun onFetchedRepositories(searchRepositoryResponse: SearchRepositoryResponse) {
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
    }

    override fun hideLoading() {
        progress_bar?.visibility = View.INVISIBLE
    }

    override fun onError(message: String?) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar(getString(R.string.something_went_wrong))
        }
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(@StringRes resId: Int) {
        showMessage(getString(resId))
    }


    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById<View>(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        snackbar.show()
    }

    override fun onDestroy() {
        searchRepositoryMvpPresenter.onDetach()
        super.onDestroy()
    }

    @SuppressLint("CheckResult")
    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }


}
