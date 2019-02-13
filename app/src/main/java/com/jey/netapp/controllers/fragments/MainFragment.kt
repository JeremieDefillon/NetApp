package com.jey.netapp.controllers.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.jey.netapp.R
import com.jey.netapp.models.GithubUser
import com.jey.netapp.utils.GithubStreams
import com.jey.netapp.utils.ItemClickSupport
import com.jey.netapp.views.GithubUserAdapter
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

class MainFragment : Fragment(), GithubUserAdapter.Listener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    //FOR DATA
    private var disposable: Disposable? = null
    private var githubUsers: MutableList<GithubUser>? = null
    private var adapter: GithubUserAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = recycler_view
        swipeRefreshLayout = swipe_refresh_layout

        this.configureRecyclerView()
        this.configureSwipeRefreshLayout()
        this.configureOnClickRecyclerView()
        this.executeHttpRequestWithRetrofit()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.disposeWhenDestroy()
    }

    // -----------------
    // ACTION
    // -----------------

    private fun configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_main_item)
                .setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
                    override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {
                        val user = adapter!!.getUser(position)
                        Toast.makeText(context, "You clicked on user : " + user.login!!, Toast.LENGTH_SHORT).show()
                    }
                })
    }

    override fun onClickDeleteButton(position: Int) {
        val user = adapter!!.getUser(position)
        Toast.makeText(context, "You are trying to delete user : " + user.login!!, Toast.LENGTH_SHORT).show()
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private fun configureRecyclerView() {

        this.githubUsers = ArrayList()
        // Create adapter passing in the sample user data
        this.adapter = GithubUserAdapter(this.githubUsers as ArrayList<GithubUser>, Glide.with(this), this)
        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = this.adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener { executeHttpRequestWithRetrofit() }
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private fun executeHttpRequestWithRetrofit() {
        this.disposable = GithubStreams.streamFetchUserFollowing("JakeWharton").subscribeWith(object : DisposableObserver<List<GithubUser>>() {
            override fun onNext(users: List<GithubUser>) {
                updateUI(users)
            }

            override fun onError(e: Throwable) {}

            override fun onComplete() {}
        })
    }

    private fun disposeWhenDestroy() {
        if (this.disposable != null && !this.disposable!!.isDisposed) this.disposable!!.dispose()
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private fun updateUI(users: List<GithubUser>) {
        githubUsers!!.clear()
        githubUsers!!.addAll(users)
        adapter!!.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing = false
    }
}
