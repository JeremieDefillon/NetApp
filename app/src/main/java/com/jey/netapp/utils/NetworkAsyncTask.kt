package com.jey.netapp.utils

import android.util.Log

import java.lang.ref.WeakReference

class NetworkAsyncTask(callback: Listeners) : android.os.AsyncTask<String, Void, String>() {

    private val callback: WeakReference<Listeners> = WeakReference(callback)

    interface Listeners {
        fun onPreExecute()
        fun doInBackground()
        fun onPostExecute(success: String)
    }

    override fun onPreExecute() {
        super.onPreExecute()
        this.callback.get()!!.onPreExecute()
        Log.e("TAG", "AsyncTask is started.")
    }

    override fun onPostExecute(success: String) {
        super.onPostExecute(success)
        this.callback.get()!!.onPostExecute(success)
        Log.e("TAG", "AsyncTask is finished.")
    }

    override fun doInBackground(vararg url: String): String {
        this.callback.get()!!.doInBackground()
        Log.e("TAG", "AsyncTask doing some big work...")
        return MyHttpURLConnection.startHttpRequest(url[0])
    }
}
