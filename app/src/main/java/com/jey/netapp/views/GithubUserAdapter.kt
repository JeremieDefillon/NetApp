package com.jey.netapp.views

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.bumptech.glide.RequestManager
import com.jey.netapp.models.GithubUser
import com.jey.netapp.R

class GithubUserAdapter// CONSTRUCTOR
(// FOR DATA
        private val githubUsers: List<GithubUser>, private val glide: RequestManager, // FOR COMMUNICATION
        private val callback: Listener) : RecyclerView.Adapter<GithubUserViewHolder>() {

    interface Listener {
        fun onClickDeleteButton(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_main_item, parent, false)

        return GithubUserViewHolder(view)
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    override fun onBindViewHolder(viewHolder: GithubUserViewHolder, position: Int) {
        viewHolder.updateWithGithubUser(this.githubUsers[position], this.glide, this.callback)
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    override fun getItemCount(): Int {
        return this.githubUsers.size
    }

    fun getUser(position: Int): GithubUser {
        return this.githubUsers[position]
    }
}
