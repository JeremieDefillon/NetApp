package com.jey.netapp.views

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jey.netapp.models.GithubUser
import kotlinx.android.synthetic.main.fragment_main_item.view.*
import java.lang.ref.WeakReference

class GithubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var textView: TextView = itemView.title
    var texViewWebsite: TextView = itemView.website
    var imageView: ImageView = itemView.image
    var imageButton: ImageButton = itemView.delete

    private var callbackWeakRef: WeakReference<GithubUserAdapter.Listener>? = null

    fun updateWithGithubUser(githubUser: GithubUser, glide: RequestManager, callback: GithubUserAdapter.Listener) {
        textView.text = githubUser.login
        this.texViewWebsite.text = githubUser.html_url
        glide.load(githubUser.avatar_url).apply(RequestOptions.circleCropTransform()).into(imageView!!)
        this.imageButton.setOnClickListener(this)
        this.callbackWeakRef = WeakReference(callback)
    }

    override fun onClick(view: View) {
        val callback = callbackWeakRef!!.get()
        callback?.onClickDeleteButton(adapterPosition)
    }
}
